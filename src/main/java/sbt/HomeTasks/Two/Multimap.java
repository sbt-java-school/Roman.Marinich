package main.java.sbt.HomeTasks.Two;

import java.util.Collection;
import java.util.Set;

/**
 * Created by SBTJavastudent on 25.08.2016.
 */

public interface Multimap<K, V> {
    int size();
    boolean isEmpty();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    Collection<V> get(Object key);
    boolean put(K key, V value);
    boolean remove(Object key);
    boolean putAll(K key, Iterable<? extends V> values);
    void clear();
    Set<K> keySet();
    Collection<V> values();
}
