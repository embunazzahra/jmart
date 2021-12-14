package com.DhauEmbunAzzahraJmartPK;

import java.util.Iterator;
import java.util.Vector;
import java.util.function.Function;

/**
 * This is class for representing Object Pool Thread.
 *
 * @author Dhau' Embun Azzahra
 * */
public class ObjectPoolThread<T> extends Thread{
    private boolean exitSignal = false;
    private Vector<T> objectPool = new Vector<>();
    private Function<T,Boolean> routine;

    /**
     * Creates Object Pool Thread.
     * @param name The thread's name.
     * @param routine The function to be applied.
     */
    public ObjectPoolThread(String name, Function<T,Boolean> routine){
        super(name);
        this.routine = routine;
    }

    /**
     * Creates Object Pool Thread.
     * @param routine The function to be applied.
     */
    public ObjectPoolThread(Function<T,Boolean> routine){
        this.routine = routine;
    }

    public synchronized void add(T object){
        objectPool.add(object);
        this.notify();
    }

    public int size(){
        return objectPool.size();
    }

    public synchronized void exit(){
        exitSignal = true;
    }

    @Override
    public void run() {
            try {
                while (!exitSignal){
                    synchronized (this){
                        while (objectPool.isEmpty()){
                            this.wait();
                        }
                    }

                    for(Iterator<T> iterator = objectPool.iterator(); iterator.hasNext();){
                        if(routine.apply(iterator.next())){
                            iterator.remove();
                        }
                    }
                }
            } catch (InterruptedException e) {

            }
    }
}
