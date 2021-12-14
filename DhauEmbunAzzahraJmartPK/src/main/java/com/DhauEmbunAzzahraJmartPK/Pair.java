package com.DhauEmbunAzzahraJmartPK;

/**
 * This is class for representing a pair of two object
 * @param <T> This is the type parameter of first object
 * @param <U> This is the type parameter of second object
 */
public class Pair<T,U> {
    public T first;
    public U second;

    public Pair(){}

    /**
     * This is class for representing a pair of two object
     * @param first This is the type parameter of first object
     * @param second This is the type parameter of second object
     */
    public Pair(T first, U second){
        this.first = first;
        this.second = second;
    }
}
