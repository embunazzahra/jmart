package DhauEmbunAzzahraJmartPK;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class Algorithm {
    private Algorithm(){}
    public static <T> int count(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }
    public static <T> int count(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return count(it,value);
    }
    public static <T> int count(Iterator<T> iterator, T value){
        final Predicate<T> prediction = value ::equals;
        return count(iterator,prediction);
    }
    public static <T> int count(T[] array, Predicate<T> pred){
        int counter=0;
        for(T i : array){
            if (i==pred){
                counter++;
            }
        }
        return counter;
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred){
        return 1;
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred){
        return 1;
    }
    public static <T> boolean exists(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it,value);
    }
    public static <T> boolean exists(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return exists(it,value);
    }
    public static <T> boolean exists(Iterator<T> iterator, T value){
        return exists(iterator,value);
    }
    public static <T> boolean exists(T[] array, Predicate<T> pred){
        return true;
    }
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        return true;
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        return true;
    }
    public static <T> boolean find(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it,value);
    }
    public static <T> boolean find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return exists(it,value);
    }
    public static <T> boolean find(Iterator<T> iterator, T value){
        return true;
    }
    public static <T> boolean find(T[] array, Predicate<T> pred){
        return true;
    }
    public static <T> boolean find(Iterable<T> iterable, Predicate<T> pred){
        return true;
    }
    public static <T> boolean find(Iterator<T> iterator, Predicate<T> pred){
        return true;
    }
    public static <T> int min(T first, T second){
       return 0;
    }
    public static <T> int max(T first, T second){
        return 0;
    }


}
