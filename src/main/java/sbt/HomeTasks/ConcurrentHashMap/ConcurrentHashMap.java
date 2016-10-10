package sbt.HomeTasks.ConcurrentHashMap;

import com.google.common.collect.Iterators;
import sbt.HomeTasks.task01.*;
import sbt.HomeTasks.task02.Multimap;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ConcurrentHashMap<K, V> implements Multimap<K, V> {
    private List<ReentrantLock> reentrantLock;
    private List<List<Pair<K, ArrayList<V>>>> map;

    private int maxSize;
    private int realSize;
    private double loadFactor;

    public ConcurrentHashMap() {
        maxSize = 4;

        reentrantLock = new ArrayList<>(4);
        for(int i = 0; i < maxSize; ++i) {
            reentrantLock.add(new ReentrantLock());
        }

        map = new ArrayList<>();
        for(int i = 0; i < maxSize; ++i) {
            map.add(new ArrayList<>());
        }
    }

    private void reHashAll() {
        List<Pair<K, V>> list = new ArrayList<>(realSize);
        for(int i = 0; i < map.size(); ++i) {
            if (map.get(i) == null) {
                continue;
            }
            for(int j = 0; j < map.get(i).size(); ++j) {
                if (map.get(i) == null ) {
                    continue;
                }
                for(int k = 0; k < map.get(i).get(j).getRight().size(); ++k) {
                    list.add(new Pair<>(map.get(i).get(j).getLeft(), map.get(i).get(j).getRight().get(k)));
                }
            }
        }

        maxSize *= 2;
        map = new ArrayList<>(maxSize);
        for (int i = 0; i < maxSize; ++i) {
            map.add(new ArrayList<>());
        }

        reentrantLock = new ArrayList<>(maxSize);
        for(int i = 0; i < maxSize; ++i) {
            reentrantLock.add(new ReentrantLock());
        }

        list.forEach(r -> put(r.getLeft(), r.getRight()));
    }

    @Override
    public int size() {
        return (int) map.stream().filter(t -> !t.isEmpty()).count();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }


    public boolean containsKey(K key) {
        return map.stream()
                .anyMatch(t -> t.stream().anyMatch(q -> q.getLeft().equals(key)));
    }

    @Override
    public boolean containsValue(Object value) {
        return map.stream()
                .anyMatch(t -> t.stream().anyMatch(q -> q.getRight().contains(value) ));
    }


    public Collection<V> get(K key) {
        int value = key.hashCode()%map.size();

        Optional<Pair<K, ArrayList<V>>> pair = map.get(value).stream()
                    .filter(t -> t.getLeft().equals(key))
                    .findFirst();

        if (pair.isPresent()) {
            return pair.get().getRight();
        } else {
            return null;
        }
    }

    @Override
    public boolean put(K key, V value) {
        boolean result = false;

        realSize++;
        loadFactor = (double) realSize/maxSize;

        if (loadFactor > 0.8) {
            reHashAll();
        }

        int hashValue = key.hashCode()%maxSize;
        reentrantLock.get(hashValue).lock();

        Optional<Pair<K, ArrayList<V>>> pairOptional = map.get(hashValue).stream().filter(t -> t.getLeft().equals(key)).findFirst();

        if (pairOptional.isPresent()) {
            result = true;
            ArrayList<V> arrayList = pairOptional.get().getRight();
            arrayList.add(value);
        } else {
            ArrayList<V> arrayList = new ArrayList<>();
            arrayList.add(value);
            Pair<K, ArrayList<V>> pair = new Pair<>(key, arrayList);
            map.get(hashValue).add(pair);
        }

        reentrantLock.get(hashValue).unlock();

        return result;
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> values) {
        int countOfIterable = Iterators.size((Iterator<?>) values);

        if (maxSize <= map.size() + countOfIterable) {
            reHashAll();
        }

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
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.stream().flatMap(t -> t.stream()).map(q -> q.getLeft()).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return null;
    }


    public boolean remove(K key) {
        boolean result = false;

        int hashValue = key.hashCode()%maxSize;
        reentrantLock.get(hashValue).lock();

        List<Pair<K, ArrayList<V>>> arrayList = map.get(hashValue);

        if (arrayList != null) {
            result = true;
            map.get(hashValue).clear();
        }

        reentrantLock.get(hashValue).unlock();
        return result;
    }

}
