package com.LaurenChristyJSleepRJ.dbjson;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class JsonDBEngine
{
    /** Interval in milliseconds for {@link #autosaver()} */
    public static long autosaveIntervalMS = 10000;
    public static long sleepIntervalMS = 100;

    private static boolean exitSignal = false;
    private static Thread autosaveThread;
    private static final HashMap<String, JsonTable<?>> loadedJsonTable = new HashMap<>();

    public static void Run(Class<?> sourceClass)
    {
        if (autosaveThread != null)
        {
            System.err.println("JsonDBEngine already launched!");
            return;
        }

        try
        {
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
            scanner.addIncludeFilter(new AnnotationTypeFilter(JsonAutowired.class));
            for (BeanDefinition bd : scanner.findCandidateComponents(sourceClass.getPackageName()))
            {
                Class<?> classContainer = Class.forName(bd.getBeanClassName());
                for (Field f : classContainer.getFields())
                {
                    JsonAutowired ann = f.getAnnotation(JsonAutowired.class);
                    if (ann == null) continue;
                    // annotation content
                    Class<?> value  = ann.value();
                    String filepath = ann.filepath();
                    // load json database table.
                    JsonTable<?> table = loadedJsonTable.get(filepath);
                    if (table == null)
                    {
                        table = new JsonTable<>(value, filepath);
                        loadedJsonTable.put(filepath, table);
                        // if type is Serializable, find maximum id in the list
                        // so the next instantiation can follow up the counter
                        if (!table.isEmpty() && Serializable.class.isAssignableFrom(value))
                        {
                            Serializable serial = Collections.max((JsonTable<? extends Serializable>) table);
                            if (serial != null)
                                Serializable.setClosingId((Class<? extends Serializable>) value, serial.id);
                        }
                    }
                    f.setAccessible(true);
                    f.set(null, table);
                }
            }
            autosaveThread = new Thread(JsonDBEngine::autosaver);
            autosaveThread.start();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            System.exit(1);
        }
    }

    private static void autosaver()
    {
        while (!exitSignal)
        {
            long start   = System.currentTimeMillis();
            long elapsed = 0;
            while (elapsed < autosaveIntervalMS && !exitSignal)
            {
                try { Thread.sleep(sleepIntervalMS); }
                catch (Throwable e) { e.printStackTrace(); }
                elapsed = System.currentTimeMillis() - start;
            }
            loadedJsonTable.forEach((f, t) -> {
                try { t.writeJson(); }
                catch (IOException e) { e.printStackTrace(); }
            });
        }
    }

    @PreDestroy
    public static void join()
    {
        System.out.println("JsonDBEngine Saving Changes...");
        exitSignal = true;
        while (autosaveThread.isAlive());
    }
}
