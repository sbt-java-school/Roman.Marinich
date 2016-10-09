package sbt.HomeTasks.task02;

import java.util.*;
import java.util.stream.Collectors;

import sbt.HomeTasks.task02.*;


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
    public boolean containsKey(K key) {
        return innerMap.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        for (Map.Entry<K, ArrayList<V>> entry : innerMap.entrySet()) {
            if (entry.getValue().contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<V> get(K key) {
        return innerMap.get(key);
    }

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        ArrayList<V> arrayList = innerMap.get(key);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            result = true;
        }
        arrayList.add(value);
        innerMap.put(key, arrayList);
        return result;
    }

    @Override
    public boolean remove(K key) {
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
        return innerMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

}