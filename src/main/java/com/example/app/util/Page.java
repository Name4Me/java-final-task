package com.example.app.util;

import java.util.List;

/**
 * This class is used to encapsulate information about page of items.
 *
 * @param <T> Type parameter of items
 */
public class Page<T> {

    private final List<T> items;
    private final Integer number;
    private final Integer size;
    private final Integer currentSize;
    private final boolean first;
    private final boolean last;

    public Page(List<T> items, Integer number, Integer size) {
        this.items = items;
        this.number = number;
        this.size = size;
        currentSize = items.size();
        first = number == 1;
        last = currentSize <= size;
        if (!last) {
            this.items.remove(currentSize - 1);
        }
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }
}
