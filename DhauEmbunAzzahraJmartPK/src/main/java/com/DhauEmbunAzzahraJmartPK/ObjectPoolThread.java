package com.DhauEmbunAzzahraJmartPK;

import java.util.Iterator;
import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread{
    private boolean exitSignal = false;
    private Vector<T> objectPool = new Vector<>();
    private Function<T,Boolean> routine;

    public ObjectPoolThread(String name, Function<T,Boolean> routine){
        super(name);
        this.routine = routine;
    }

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

                    /*for (int i = 0 ; i < objectPool.size() && objectPool.size()>0; i++){
                        if(routine.apply(objectPool.get(i))){
                            objectPool.remove(i);
                            i--;
                        }
                    }*/
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
