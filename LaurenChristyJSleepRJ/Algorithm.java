package LaurenChristyJSleepRJ;

import java.util.stream.Collectors;
import java.util.*;

public class Algorithm {
	// PT Modul 5
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        List<T> newList = new ArrayList<T>();
        for (final T var : iterable){
            if (var.equals(value))
                newList.add(var);
            else return null;
        }return newList;
    }
    public static <T> List<T> collect(Iterable<T> iterable,Predicate<T> predikat){
        List<T> newList = new ArrayList<T>();
        for (final T var : iterable){
            if(predikat.predicate(var))
                newList.add(var);
            else return null;
        }return newList;
    }
    public static <T> List<T> collect(T[] array,T value) {
        List<T> newList = new ArrayList<T>();
        for (final T var : array){
            if (var.equals(value))
                newList.add(var);
            else return null;
        }return newList;
    }
    public static <T> List<T> collect(T[] array,Predicate<T> predikat){
        List<T> newList = new ArrayList<T>();
        for (final T var : array){
            if (var.equals(predikat))
                newList.add(var);
            else return null;
        }return newList;
    }
    public static <T> List<T>collect(Iterator<T> iterator,Predicate<T> predikat) {
        List<T> newList = new ArrayList<T>();
        for (Iterator<T> iter = iterator; iter.hasNext();){
        	T var = iter.next();
        	if (var.equals(predikat))
                newList.add(var);
            else return null;
        }return newList;
    }
    public static <T> List<T>collect(Iterator<T> iterator,T value) {
        List<T> newList = new ArrayList<T>();
        for (Iterator<T> iter = iterator; iter.hasNext();){
        	T var = iter.next();
            if (var.equals(value))
                newList.add(var);
            else return null;
        }return newList;
    }
    public static <T> List<T> paginate(Iterator<T> iterator,int page,int pageSize,Predicate<T> predikat) {
        List<T> newList = new ArrayList<T>();
        while (iterator.hasNext()) {
            final T var = iterator.next();
            if (predikat.equals(var))
                newList.add(var);
        }return newList;
    }
    public static <T> List<T> paginate(T[] array,int page,int pageSize,Predicate<T> predikat) {
        final Iterator<T> var = Arrays.stream(array).iterator();
        return paginate(var, page, pageSize, predikat);
    }
    public static <T> List<T> paginate(Iterable<T> iterable,int page,int pageSize,Predicate<T> predikat) {
        final Iterator<T> var = iterable.iterator();
        return paginate(var,page,pageSize,predikat);
    }
    
    // CS Modul 5
	private Algorithm(){	
	}
    public static <T> int count(Iterator<T> iterator, T value) {
        final Predicate <T> var = value::equals;
        return count(iterator,var);
    }
	public static <T> int count(T[] array, T value){
        final Iterator<T> var = Arrays.stream(array).iterator();
        return count(var,value);
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> predikat) {
        final Iterator<T> var = iterable.iterator();
        return count(var, predikat);
    }
    public static <T> int count(Iterable<T> iterable, T value) {
        final Iterator <T> var = iterable.iterator();
        return count(var,value);
    }
    public static <T> int count(T[] array, Predicate<T> predikat) {
        final Iterator<T> var = Arrays.stream(array).iterator();
        return count(var, predikat);
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> predikat) {
        return count(iterator, predikat);
    }
    public static <T> boolean exists(T[] array, Predicate<T> predikat) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        return exists(iterator, predikat);
    }
    public static <T> boolean exists(T[] array, T value) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        Predicate<T> predikat = value::equals;
        return exists(iterator, value);
    }
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        Predicate<T> predikat = value::equals;
        return exists(iterable, predikat);
    }
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> predikat) {
        return exists(iterable.iterator(), predikat);
    }
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        Predicate<T> predikat = value::equals;
        return exists(iterator, predikat);
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> predikat) {
        while(iterator.hasNext()) {
            if(predikat.predicate(iterator.next()))
                return true;
        }return false;
    }
}