package sbt.HomeTasks.ConcurrentHashMap;

import com.google.common.collect.Iterators;
import sbt.HomeTasks.task01.*;
import sbt.HomeTasks.task02.Multimap;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentHashMap<K, V> implements Multimap<K, V> {

    private List<ReentrantLock> reentrantLock;
    private List<List<Pair<K, ArrayList<V>>>> map;
    private int maxSize;

    public ConcurrentHashMap() {
        reentrantLock = new ArrayList<>();
        map = new ArrayList<>();
        maxSize = 4;
    }

    private void reHashAll() {
        List<Pair<K, V>> list = new ArrayList<>(map.size());
        for(int i = 0; i < list.size(); ++i) {
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

        list.forEach(r -> put(r.getLeft(), r.getRight()));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }


    public boolean containsKey(K key) {
        return map.stream()
                .anyMatch(t -> t.stream().anyMatch(q -> q.equals(key)));
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

        if (maxSize == map.size()) {
            reHashAll();
        }

        int hashValue = key.hashCode()%map.size();
        reentrantLock.get(hashValue).lock();

        Optional<Pair<K, ArrayList<V>>> pairOptional = map.get(hashValue).stream().filter(t -> t.getLeft().equals(key)).findFirst();

        if (pairOptional.isPresent()) {
            result = true;
            ArrayList<V> arrayList = pairOptional.get().getRight();
            arrayList.add(value);
        } else {
            ArrayList<V> arrayList = new ArrayList<V>();
            Pair<K, ArrayList<V>> pair = new Pair<>(key, arrayList);
            map.get(hashValue).add(pair);
        }

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
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }


    public boolean remove(K key) {
        boolean result = false;

        int hashValue = key.hashCode()%map.size();
        reentrantLock.get(hashValue).lock();

        List<Pair<K, ArrayList<V>>> arrayList = map.get(hashValue);

        if (arrayList != null) {
            result = true;
            map.set(hashValue, null);
        }

        reentrantLock.get(hashValue).unlock();
        return result;
    }

}
