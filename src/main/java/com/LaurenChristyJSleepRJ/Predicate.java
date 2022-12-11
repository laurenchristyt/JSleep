package com.LaurenChristyJSleepRJ;
/**
 * A functional interface that defines a method for evaluating an object and
 * returning a boolean result.
 *
 * @param <T> The type of object to be evaluated by this Predicate.
 * @author Lauren Christy Tanudjaja
 */
public interface Predicate <T> {
    public abstract boolean predicate(T args);
}
