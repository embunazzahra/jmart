package DhauEmbunAzzahraJmartPK;

import java.util.*;

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
        final Predicate<T> pred = value ::equals;
        return count(iterator,pred);
    }
    public static <T> int count(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it,pred);
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred){
        int count = 0;
        while (iterator.hasNext()){
            if(pred.predicate(iterator.next())){
                count++;
            }
        }
        return count;
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
        final Predicate<T> pred = value :: equals;
        return exists(iterator,pred);
    }
    public static <T> boolean exists(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it,pred);
    }
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return exists(it,pred);
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while (iterator.hasNext()){
            T curr = iterator.next();
            if(pred.predicate(curr)){
                return true;
            }
        }
        return false;
    }
    public static <T> T find(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it,value);
    }
    public static <T> T find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return find(it,value);
    }
    public static <T> T find(Iterator<T> iterator, T value){
        final Predicate<T> pred = value :: equals;
        return find(iterator,pred);
    }
    public static <T> T find(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return find(it,pred);
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while (iterator.hasNext()){
            T curr = iterator.next();
            if(pred.predicate(curr)){
                return curr;
            }
        }
        return null;
    }

    //max
    public static <T extends Comparable<? super T>> T max(T first, T second){
        if(first.compareTo(second)>0){
            return first;
        }
        else {
            return second;
        }
    }
    public static <T extends Comparable<? super T>> T max(T[] array){
        final Iterator<T> iterator = Arrays.stream(array).iterator();
        return max(iterator);
    }
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable){
        final Iterator<T> iterator = iterable.iterator();
        return max(iterator);
    }
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator){
        T maxim = iterator.next();
        while (iterator.hasNext()){
            T current = iterator.next();
            if(maxim.compareTo(current)<0){
                maxim = current;
            }
        }
        return maxim;
    }
    public static <T extends Comparator<? super T>> T max(T first, T second, Comparator<? super T> comparator){
        if(comparator.compare(first,second)>=0){
            return first;
        }
        else {
            return second;
        }
    }
    public static <T extends Comparator<? super T>> T max(T[] array, Comparator<? super T> comparator){
        final Iterator<T> iterator = Arrays.stream(array).iterator();
        return max(iterator,comparator);
    }
    public static <T extends Comparator<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        final Iterator<T> iterator = iterable.iterator();
        return max(iterator, comparator);
    }
    public static <T extends Comparator<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T maxim = iterator.next();
        while (iterator.hasNext()){
            T current = iterator.next();
            if(comparator.compare(maxim,current)<0){
                maxim = current;
            }
        }
        return maxim;
    }

    //min
    public static <T extends Comparable<? super T>> T min(T first, T second){
        if(first.compareTo(second)<0){
            return first;
        }
        else {
            return second;
        }
    }
    public static <T extends Comparable<? super T>> T min(T[] array){
        final Iterator<T> iterator = Arrays.stream(array).iterator();
        return min(iterator);
    }
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable){
        final Iterator<T> iterator = iterable.iterator();
        return min(iterator);
    }
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator){
        T minim = iterator.next();
        while (iterator.hasNext()){
            T current = iterator.next();
            if(minim.compareTo(current)>=0){
                minim = current;
            }
        }
        return minim;
    }
    public static <T extends Comparator<? super T>> T min(T first, T second, Comparator<? super T> comparator){
        if(comparator.compare(first,second)<=0){
            return first;
        }
        else {
            return second;
        }
    }
    public static <T extends Comparator<? super T>> T min(T[] array, Comparator<? super T> comparator){
        final Iterator<T> iterator = Arrays.stream(array).iterator();
        return min(iterator,comparator);
    }
    public static <T extends Comparator<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        final Iterator<T> iterator = iterable.iterator();
        return min(iterator, comparator);
    }
    public static <T extends Comparator<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T minim = iterator.next();
        while (iterator.hasNext()){
            T current = iterator.next();
            if(comparator.compare(minim,current)>=0){
                minim = current;
            }
        }
        return minim;
    }

    //collect
    public static <T> List<T> collect(T[] array, T value){
        final Iterator<T> iterator = Arrays.stream(array).iterator();
        return collect(iterator,value);
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        Iterator<T> iterator = iterable.iterator();
        return collect(iterator,value);
    }
    public static <T> List<T> collect(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return collect(iterator,pred);
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred){
        final Iterator<T> iterator = Arrays.stream(array).iterator();
        return collect(iterator,pred);
    }
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> iterator = iterable.iterator();
        return collect(iterator,pred);
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<T>();
        while (iterator.hasNext()){
            T it = iterator.next();
            if(pred.predicate(it)){
                list.add(it);
            }
        }
        return list;
    }

}
