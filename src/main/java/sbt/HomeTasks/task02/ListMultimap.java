package main.java.sbt.HomeTasks.task02;

import java.util.*;
import java.util.stream.Collectors;

public class ListMultimap<K, V> implements Multimap<K, V> {
    private Map<K, ArrayList<V>> innerMap = new HashMap<>();

    @Override
    public int size() {
        return innerMap.size();
    }

    @Override
    public boolean isEmpty() {
        return innerMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return innerMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for(Map.Entry<K, ArrayList<V>> entry : innerMap.entrySet() ) {
            if (entry.getValue().contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<V> get(Object key) {
        return innerMap.get(key);
    }

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        ArrayList arrayList = innerMap.get(key);
        if (arrayList == null) {
            arrayList = new ArrayList();
            result = true;
        }
        arrayList.add(value);
        innerMap.put(key, arrayList);
        return result;
    }

    @Override
    public boolean remove(Object key) {
        ArrayList arrayList = innerMap.get(key);
        if (arrayList == null) {
            return false;
        }
        innerMap.remove(key);
        return true;
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> values) {
        boolean result = true;
        for (V value : values) {
            if (put(key, value)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        innerMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return innerMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return innerMap.values().stream().flatMap(t -> t.stream()).collect(Collectors.toList());
    }

}