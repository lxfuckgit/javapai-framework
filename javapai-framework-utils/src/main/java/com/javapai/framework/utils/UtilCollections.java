package com.javapai.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @deprecated 建议优先使用goole-guava-collections工具包.
 * 
 * @author pooja
 *
 */
@Deprecated
public final class UtilCollections {
	/**
	 * Calculates initial capacity needed to hold <code>size</code> elements in
	 * a HashMap or Hashtable without forcing an expensive increase in internal
	 * capacity. Capacity is based on the default load factor of .75.
	 * <p>
	 * Usage: <code>Map map = new HashMap(HashMapUtils.calcCapacity(10));<code>
	 * </p>
	 * 
	 * @param size
	 *            the number of items that will be put into a HashMap
	 * @return initial capacity needed
	 */
	public static final int calcCapacity(int size) {
		return ((size * 4) + 3) / 3;
	}

	/**
	 * Creates a new <code>HashMap</code> that has all of the elements of
	 * <code>map1</code> and <code>map2</code> (on key collision, the latter
	 * override the former).
	 *
	 * @param map1
	 *            the fist hashmap to merge
	 * @param map2
	 *            the second hashmap to merge
	 * @return new hashmap
	 */
	public static HashMap merge(Map map1, Map map2) {
		HashMap retval = new HashMap(calcCapacity(map1.size() + map2.size()));

		retval.putAll(map1);
		retval.putAll(map2);

		return retval;
	}
}
