package ua.axiom.apply.map;

import java.util.Optional;

public interface HashMap {
    /**
     * Puts new key-val pair inside, or replaces value if key already exists
     * @param key integer key for the map
     * @param value long value
     * @return  true if key was already present
     *          false if key is not present
     */
    boolean put(int key, long value);
    Optional<Long> get(int key);
    int size();
}
