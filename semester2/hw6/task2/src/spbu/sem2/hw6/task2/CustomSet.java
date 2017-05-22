package spbu.sem2.hw6.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class CustomSet<Type> implements Set<Type> {
    private ArrayList<Type> set = new ArrayList<>();

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<Type> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(Type type) {
        if (contains(type)) {
            return false;
        }
        return set.add(type);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Type> c) {
        boolean changed = false;
        for (Type i: c) {
            if (!set.contains(i)) {
                set.add(i);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }
}
