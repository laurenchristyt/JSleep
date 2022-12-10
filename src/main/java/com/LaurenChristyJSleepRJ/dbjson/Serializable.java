package com.LaurenChristyJSleepRJ.dbjson;

import java.util.HashMap;

public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    /**
     * Constructor for objects of class Serializable
     */
    public Serializable() {
        Integer counter = mapCounter.get(getClass());
        //ternary
        counter = counter == null? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }
    public int compareTo(Serializable otherS) {
        return Integer.compare(id, otherS.id);
    }
    public boolean equals(Object obj) {
        return obj instanceof Serializable && ((Serializable) obj).id == id;
    }
    public boolean equals(Serializable serial) {
        return serial.id == id;
    }
    public static <T> Integer getClosingId(Class<T> cls) {
        return mapCounter.get(cls);
    }
    public static <T> Integer setClosingId(Class<T> cls, int i) {
        return mapCounter.put(cls, i);
    }
}
