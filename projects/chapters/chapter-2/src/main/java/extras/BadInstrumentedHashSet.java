package extras;

import java.util.Collection;
import java.util.HashSet;

public class BadInstrumentedHashSet<E> extends HashSet<E> {
    // The number of attempted element insertions
    public int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}
