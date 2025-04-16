package com.apcs.disunity.appliable;

/**
 * A node that can be applied
 * 
 * @author Qinzhao Li
 */
public interface Appliable<T> {

    /* ================ [ METHODS ] ================ */

    // Apply
    public T apply(T original, double delta);

}
