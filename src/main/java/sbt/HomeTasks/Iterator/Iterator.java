package main.java.sbt.HomeTasks.Iterator;


import java.util.List;

public class Iterator<T> {
    private T[] array;

    public Iterator(List<T> list) {
        array = (T[]) list.toArray();
    }

    public Element begin() {
        return new Element(0);
    }

    public Element end() {
        return new Element(array.length);
    }

    public class Element {
        int point = 0;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Element element = (Element) o;

            return point == element.point;

        }

        @Override
        public int hashCode() {
            throw new RuntimeException("Operation not supported");
        }

        public Element(int point) {
            this.point = point;
        }

        public void next() {
            point++;
        }

        public void prev() {
            point--;
        }

        public void setElement(T element) {
            array[point] = element;
        }

        public T getElement() {
            return array[point];
        }
    }
}
