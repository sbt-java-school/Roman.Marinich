package sbt.HomeTasks.task02;

import java.util.Collection;
import java.util.Set;

public interface Multimap<K, V> {
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    Collection<V> get(K key);
    boolean put(K key, V value);
    boolean remove(K key);
    boolean putAll(K key, Iterable<? extends V> values);
    void clear();
    Set<K> keySet();
    Collection<V> values();
}
