package com.DhauEmbunAzzahraJmartPK;

import java.util.*;

/** provides basic algorithm
 * @author Ramadhan Kalih Sewu
 * @author Dhau' Embun Azzahra
 */
public class Algorithm
{
    private Algorithm() {}

    /**
     * Method to count the number of certain value in an array
     * @param array The array which we want to count the certain value with.
     * @param value The value we want to check.
     * @param <T> This describes the type parameter
     * @return the number of certain value in an array.
     */
    public static <T> int count(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    /**
     * Method to count the number of certain value in an Iterable object
     * @param iterable The object which we want to count the certain value with.
     * @param value The value we want to check.
     * @param <T> This describes the type parameter.
     * @return the number of certain value in an object.
     */
    public static <T> int count(Iterable<T> iterable, T value)
    {
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }

    /**
     * Method to count the number of certain value in an Iterator object
     * @param iterator The object which we want to count the certain value with.
     * @param value The value we want to check.
     * @param <T> This describes the type parameter.
     * @return the number of certain value in an object.
     */
    public static <T> int count(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    /**
     * Method to count the number of array's item which match to certain criteria
     * @param array The array which we want to count the items with certain criteria.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return the number of item which match the certain criteria in an object.
     */
    public static <T> int count(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    /**
     * Method to count the number of iterable object's item which match to certain criteria
     *
     * @param iterable The object which we want to count the items with certain criteria.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return The number of item which match the certain criteria in an object.
     */
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    /**
     *  Method to count the number of iterator object's item which match to certain criteria
     * @param iterator The object which we want to count the items with certain criteria.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return The number of item which match the certain criteria in an object.
     */
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred)
    {
        int count = 0;
        while (iterator.hasNext())
            if (pred.predicate(iterator.next()))
                ++count;
        return count;
    }

    /**
     * Method to find a value in an array
     * @param array The array we want to check.
     * @param value The value we want to find
     * @param <T> This describes the type parameter.
     * @return the matched object we find in array
     */
    public static <T> T find(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    /**
     * Method to find a value in an object
     * @param iterable The object we want to check.
     * @param value The value we want to find
     * @param <T> This describes the type parameter.
     * @return the matched object we find in Iterable object
     */
    public static <T> T find(Iterable<T> iterable, T value)
    {
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    /**
     * Method to find a value in an object
     * @param iterator The object we want to check.
     * @param value The value we want to find.
     * @param <T> This describes the type parameter.
     * @return the matched object we find in Iterator object.
     */
    public static <T> T find(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }

    /**
     * Method to find an object with criteria in an array
     * @param array The array we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return the matched object we find in array.
     */
    public static <T> T find(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }

    /**
     * Method to find an object with criteria in an Iterable object.
     * @param iterable The object we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return the matched object we find in Iterable object.
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    /**
     * Method to find an object with criteria in an Iterator object.
     * @param iterator The object we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return the matched object we find in Iterator object.
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred)
    {
        while (iterator.hasNext())
        {
            T current = iterator.next();
            if (pred.predicate(current))
                return current;
        }
        return null;
    }

    /**
     * Method to find the index of certain value in array
     * @param array The array we want to check.
     * @param value The value we want to check.
     * @param <T> This describes the type parameter.
     * @return index number of the value
     */
    public static <T> int findIndex(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return findIndex(it, value);
    }

    /**
     * Method to find the index of certain value in Iterable object
     * @param iterable The Iterable object we want to check.
     * @param value The value we want to check.
     * @param <T> This describes the type parameter.
     * @return index number of the value
     */
    public static <T> int findIndex(Iterable<T> iterable, T value)
    {
        final Iterator<T> it = iterable.iterator();
        return findIndex(it, value);
    }

    /**
     * Method to find the index of certain value in Iterator object
     * @param iterator The Iterator object we want to check.
     * @param value The value we want to check.
     * @param <T> This describes the type parameter.
     * @return index number of the value
     */
    public static <T> int findIndex(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return findIndex(iterator, pred);
    }

    /**
     * Method to find the index of value which match certain criteria in array
     * @param array The array we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return index number of the value
     */
    public static <T> int findIndex(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return findIndex(it, pred);
    }

    /**
     * Method to find the index of value which match certain criteria in Iterable Object
     * @param iterable The Iterable object we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return index number of the value
     */
    public static <T> int findIndex(Iterable<T> iterable, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return findIndex(it, pred);
    }

    /**
     * Method to find the index of value which match certain criteria in Iterator Object
     * @param iterator The Iterator object we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> his describes the type parameter.
     * @return index number of the value
     */
    public static <T> int findIndex(Iterator<T> iterator, Predicate<T> pred)
    {
        for (int i = 0; iterator.hasNext(); ++i)
            if (pred.predicate(iterator.next()))
                return i;
        return -1;
    }

    /**
     * Method to collect certain value in array
     * @param array The array we want to check.
     * @param value The value we want to collect.
     * @param <T> This describes the type parameter.
     * @return List of certain value in array
     */
    public static <T> List<T> collect(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }

    /**
     *  Method to collect certain value in Iterable object
     * @param iterable The Iterable object we want to check.
     * @param value The value we want to collect.
     * @param <T> This describes the type parameter.
     * @return List of certain value in object
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value)
    {
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }

    /**
     * Method to collect certain value in Iterator object
     * @param iterator The Iterator object we want to check.
     * @param value The value we want to collect.
     * @param <T> This describes the type parameter.
     * @return List of certain value in object.
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }

    /**
     *  Method to collect value which match the predicate in array
     * @param array The array we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> List of certain value in array
     * @return
     */
    public static <T> List<T> collect(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }

    /**
     * Method to collect value which match the predicate in Iterable object
     * @param iterable The Iterable object we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return List of certain value in object
     */
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }

    /**
     * Method to collect value which match the predicate in Iterator object
     * @param iterator The Iterator object we want to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return List of certain value in object
     */
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred)
    {
        ArrayList<T> list = new ArrayList<>();
        while (iterator.hasNext())
        {
            T current = iterator.next();
            if (pred.predicate(current))
                list.add(current);
        }
        return list;
    }

    /**
     * Method to do pagination
     * @param array the collection we want to paginate
     * @param page the page to collect
     * @param pageSize the page size in pagination
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return list in certain page and page size
     */
    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return paginate(it, page, pageSize, pred);
    }

    /**
     * Method to do pagination
     * @param iterable the collection we want to paginate
     * @param page the page to collect
     * @param pageSize the page size in pagination
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return list in certain page and page size
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return paginate(it, page, pageSize, pred);
    }

    /**
     * Method to do pagination
     * @param iterator the collection we want to paginate
     * @param page the page to collect
     * @param pageSize the page size in pagination
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return list in certain page and page size
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred)
    {
        int occurences = 0;
        int startingIdx = page * pageSize;
        List<T> pageList = new ArrayList<>(pageSize);
        // skip first occurrences of element
        while (iterator.hasNext() && occurences < startingIdx)
        {
            T obj = iterator.next();
            if (pred.predicate(obj))
                ++occurences;
        }
        // get the next occurrences of element
        while (iterator.hasNext() && pageList.size() < pageSize)
        {
            T obj = iterator.next();
            if (pred.predicate(obj))
                pageList.add(obj);
        }
        return pageList;
    }

    /**
     * Method to check if a certain value exist in the collection
     * @param array The collection to check.
     * @param value The value to check.
     * @param <T> This describes the type parameter.
     * @return true if the certain value exist, otherwise false
     */
    public static <T> boolean exists(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }
    /**
     * Method to check if a certain value exist in the collection
     * @param iterable The collection to check.
     * @param value The value to check.
     * @param <T> This describes the type parameter.
     * @return true if the certain value exist, otherwise false
     */
    public static <T> boolean exists(Iterable<T> iterable, T value)
    {
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    /**
     * Method to check if a certain value exist in the collection
     * @param iterator The collection to check.
     * @param value The value to check.
     * @param <T> This describes the type parameter.
     * @return true if the certain value exist, otherwise false
     */
    public static <T> boolean exists(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    /**
     * Method to check if a certain value which match the predicate exist in the collection
     * @param array The collection to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return true if the certain value exist, otherwise false
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    /**
     * Method to check if a certain value which match the predicate exist in the collection
     * @param iterable The collection to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return true if the certain value exist, otherwise false
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    /**
     * Method to check if a certain value which match the predicate exist in the collection
     * @param iterator The collection to check.
     * @param pred The predicate (boolean-valued function) we want to check.
     * @param <T> This describes the type parameter.
     * @return true if the certain value exist, otherwise false
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred)
    {
        while (iterator.hasNext())
        {
            T current = iterator.next();
            if (pred.predicate(current))
                return true;
        }
        return false;
    }

    /**
     * Method to find object which has the maximum value.
     * @param first The object being compared.
     * @param second The object being compared.
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value
     */
    public static <T extends Comparable<? super T>> T max(T first, T second)
    {
        if (first.compareTo(second) > 0) return first;
        return second;
    }

    /**
     * Method to find object which has the maximum value.
     * @param array collection to compare all of the item
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value
     */
    public static <T extends Comparable<? super T>> T max(T[] array)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return max(it);
    }

    /**
     * Method to find object which has the maximum value.
     * @param iterable collection to compare all of the item
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value
     */
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable)
    {
        final Iterator<T> it = iterable.iterator();
        return max(it);
    }

    /**
     * Method to find object which has the maximum value.
     * @param iterator collection to compare all of the item
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value
     */
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator)
    {
        T biggest = null;
        while (iterator.hasNext() && biggest == null)
            biggest = iterator.next();
        while (iterator.hasNext())
        {
            T next = iterator.next();
            if (next == null) continue;
            biggest = max(biggest, next);
        }
        return biggest;
    }

    /**
     * Method to find object which has the maximum value with Comparator.
     * @param first The object being compared.
     * @param second The object being compared.
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value
     */
    public static <T> T max(T first, T second, Comparator<? super T> comparator)
    {
        if (comparator.compare(first, second) > 0) return first;
        return second;
    }

    /**
     * Method to find object which has the maximum value with Comparator.
     * @param array collection to compare all of the item
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value.
     */
    public static <T> T max(T[] array, Comparator<? super T> comparator)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return max(it, comparator);
    }

    /**
     * Method to find object which has the maximum value with Comparator.
     * @param iterable collection to compare all of the item
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value.
     */
    public static <T> T max(Iterable<T> iterable, Comparator<? super T> comparator)
    {
        final Iterator<T> it = iterable.iterator();
        return max(it, comparator);
    }

    /**
     * Method to find object which has the maximum value with Comparator.
     * @param iterator collection to compare all of the item
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the maximum value.
     */
    public static <T> T max(Iterator<T> iterator, Comparator<? super T> comparator)
    {
        T biggest = null;
        while (iterator.hasNext() && biggest == null)
            biggest = iterator.next();
        while (iterator.hasNext())
        {
            T next = iterator.next();
            if (next == null) continue;
            biggest = max(biggest, next, comparator);
        }
        return biggest;
    }

    /**
     * Method to find object which has the minimum value.
     * @param first The object being compared.
     * @param second The object being compared.
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value
     */
    public static <T extends Comparable<? super T>> T min(T first, T second)
    {
        if (first.compareTo(second) < 0) return first;
        return second;
    }

    /**
     * Method to find object which has the minimum value.
     * @param array collection to compare all of the item
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value
     */
    public static <T extends Comparable<? super T>> T min(T[] array)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return min(it);
    }

    /**
     * Method to find object which has the minimum value.
     * @param iterable collection to compare all of the item
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value
     */
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable)
    {
        final Iterator<T> it = iterable.iterator();
        return min(it);
    }

    /**
     * Method to find object which has the minimum value.
     * @param iterator collection to compare all of the item
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value
     */
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator)
    {
        T lowest = null;
        while (iterator.hasNext() && lowest == null)
            lowest = iterator.next();
        while (iterator.hasNext())
        {
            T next = iterator.next();
            if (next == null) continue;
            lowest = min(lowest, next);
        }
        return lowest;
    }

    /**
     * Method to find object which has the minimum value with Comparator.
     * @param first The object being compared.
     * @param second The object being compared.
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value
     */
    public static <T> T min(T first, T second, Comparator<? super T> comparator)
    {
        if (comparator.compare(first, second) < 0) return first;
        return second;
    }

    /**
     * Method to find object which has the minimum value with Comparator.
     * @param array collection to compare all of the item
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value.
     */
    public static <T> T min(T[] array, Comparator<? super T> comparator)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return min(it, comparator);
    }

    /**
     * Method to find object which has the minimum value with Comparator.
     * @param iterable collection to compare all of the item
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value.
     */
    public static <T> T min(Iterable<T> iterable, Comparator<? super T> comparator)
    {
        final Iterator<T> it = iterable.iterator();
        return min(it, comparator);
    }

    /**
     * Method to find object which has the minimum value with Comparator.
     * @param iterator collection to compare all of the item
     * @param comparator The comparator
     * @param <T> This describes the type parameter.
     * @return object which has the minimum value.
     */
    public static <T> T min(Iterator<T> iterator, Comparator<? super T> comparator)
    {
        T lowest = null;
        while (iterator.hasNext() && lowest == null)
            lowest = iterator.next();
        while (iterator.hasNext())
        {
            T next = iterator.next();
            if (next == null) continue;
            lowest = min(lowest, next, comparator);
        }
        return lowest;
    }
}
