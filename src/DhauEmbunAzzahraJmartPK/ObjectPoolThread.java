package DhauEmbunAzzahraJmartPK;

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
        notify();
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
                for (int i = 0 ; i < objectPool.size() ; i++){
                    if(routine.apply(objectPool.get(i))){
                        objectPool.remove(i);
                        if(objectPool.isEmpty()){
                            break;
                        }else {
                            i--;
                        }
                    }
                }
                while (objectPool.isEmpty()){
                    wait();
                }
            }
        } catch (InterruptedException e) {

        }
    }
}
