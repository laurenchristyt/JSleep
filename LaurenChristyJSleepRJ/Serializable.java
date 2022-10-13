package LaurenChristyJSleepRJ;

import java.util.HashMap;

public class Serializable implements Comparable <Serializable> {
    public static int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable() {
        Integer counter = mapCounter.get(getClass());
        if(counter==null)
            counter=0;
        else
            counter=counter+1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }
    @Override
    public int compareTo(Serializable x){
        return Integer.compare(this.id, x.id);
    }
    public boolean equals(Object x) {
        return x instanceof Serializable&&((Serializable)x).id==this.id;
    }
    protected boolean equals(Serializable x){
        return x.id==this.id;
    }

    public static<T extends Serializable> Integer getClosingId(Class<T> clazz){
        return mapCounter.get(clazz);
    }

    public static<T> Integer setClosingId(Class<T> clazz, int id){
        return mapCounter.put(clazz, id);
    }

}