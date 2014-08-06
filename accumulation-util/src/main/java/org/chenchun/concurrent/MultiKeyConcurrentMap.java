package org.chenchun.concurrent;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Simplified version of a {@link ConcurrentMap} that uses two keys. A nested map is used under the hood to make gets
 * as fast as possible.
 * 
 * @author Eric Dalquist
 * @version $Revision: 656 $
 * //@see com.googlecode.ehcache.annotations.impl.MultiKeyConcurrentMap
 */
public final class MultiKeyConcurrentMap<K1, K2, V> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final ConcurrentMap<K1, ConcurrentMap<K2, V>> map = new ConcurrentHashMap<K1, ConcurrentMap<K2,V>>();
    
    /**
     * @param key1 primary key
     * @param key2 sub key
     * @return true if the key-pair is contained
     * @see ConcurrentMap#containsKey(Object)
     */
    public boolean containsKey(K1 key1, K2 key2) {
        final ConcurrentMap<K2, V> subMap = this.map.get(key1);
        return subMap != null && subMap.containsKey(key2);
    }
    
    /**
     * @param key1 primary key
     * @param key2 sub key
     * @param value The value to add to the map
     * @return the previous value associated with the specified key or null if there was no previous value
     * @see ConcurrentMap#put(Object, Object)
     */
    public V put(K1 key1, K2 key2, V value) {
        final ConcurrentMap<K2, V> subMap = this.getOrCreateSubMap(key1);
        return subMap.put(key2, value);
    }
    
    /**
     * @param key1 primary key
     * @param key2 sub key
     * @param value the value to add to the map if no previous value exists
     * @return the previous value associated with the specified key or null if there was no previous value 
     * @see ConcurrentMap#putIfAbsent(Object, Object)
     */
    public V putIfAbsent(K1 key1, K2 key2, V value) {
        final ConcurrentMap<K2, V> subMap = this.getOrCreateSubMap(key1);
        return subMap.putIfAbsent(key2, value);
    }
    
    /**
     * @param key1 primary key
     * @param key2 sub key
     * @return the value to which the specified keys are mapped, or null if there is no value
     * @see ConcurrentMap#get(Object)
     */
    public V get(K1 key1, K2 key2) {
        final ConcurrentMap<K2, V> subMap = this.map.get(key1);
        if (subMap == null) {
            return null;
        }
        return subMap.get(key2);
    }
    
    /**
     * Get/create the subMap for the specified key
     */
    private ConcurrentMap<K2, V> getOrCreateSubMap(K1 key1) {
        ConcurrentMap<K2, V> subMap = this.map.get(key1);
        if (subMap == null) {
            subMap = new ConcurrentHashMap<K2, V>();
            final ConcurrentMap<K2, V> existingSubMap = this.map.putIfAbsent(key1, subMap);
            if (existingSubMap != null) {
                subMap = existingSubMap;
            }
        }
        return subMap;
    }
}
