package com.LaurenChristyJSleepRJ.dbjson;

import java.util.HashMap;

/**
 * A base class for objects that can be serialized and assigned an ID.
 *
 * <p>This class provides an ID field and a simple mechanism for assigning unique IDs to objects of subclasses.
 * It also provides methods for comparing objects by ID and for managing the ID counter for each subclass.</p>
 *
 * @author Lauren Christy Tanudjaja
 * @version 1.0
 */
public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    /**
     * Construct a new `Serializable` object and assign it a unique ID.
     *
     * This constructor creates a new `Serializable` object and assigns it an ID based on the ID counter for its class.
     * If this is the first object of its class to be constructed, its ID will be 1.
     * Otherwise, it will be one greater than the ID of the previous object of the same class.
     *
     * @see #mapCounter
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
