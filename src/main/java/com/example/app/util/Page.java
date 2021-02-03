package com.example.app.util;

import java.util.List;

/**
 * This class is used to encapsulate information about page of items.
 *
 * @param <T> Type parameter of items
 */
public class Page<T> {

	private List<T> items;
	private Integer number;
	private Integer size;
	private Integer currentSize;
	private boolean first;
	private boolean last;

	public Page(List<T> items, Integer number, Integer size) {
		this.items = items;
		this.number = number;
		this.size = size;

		currentSize = items.size();
		first = number == 1;
		last = currentSize < size;
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
