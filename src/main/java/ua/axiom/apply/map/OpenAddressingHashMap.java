package ua.axiom.apply.map;

import java.util.Optional;

public class OpenAddressingHashMap implements HashMap {
    public static final int DEFAULT_INITIAL_CAPACITY = 255;
    private static final float DEFAULT_FILL_FACTOR = 0.33F;
    private static final int DEFAULT_RESIZE_FACTOR = 8;

    private MapEntry[] data;
    private int presentElmCount;
    private int currentMaxCapacity;

    public OpenAddressingHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public OpenAddressingHashMap(int initialSize) {
        this.data = new MapEntry[initialSize];
        this.currentMaxCapacity = initialSize;
    }

    @Override
    public boolean put(int key, long value) {
        int hashKey = getHashKey(key);

        //  resize if map is too packet
        if(presentElmCount >= currentMaxCapacity * DEFAULT_INITIAL_CAPACITY) {
            resize(currentMaxCapacity * DEFAULT_RESIZE_FACTOR);
        }

        //  we might have to resize data array many times, until hashKey index can be assigned
        while(true) {
            //  put element
            for(int i = hashKey; i < currentMaxCapacity ; ++i) {
                if(data[i] == null) {
                    MapEntry newEntry = new MapEntry(key, value);
                    data[i] = newEntry;
                    presentElmCount++;
                    return false;
                }
                else if(data[i].getKey() == key) {
                    MapEntry oldEntry = data[i];
                    oldEntry.setValue(value);
                    return true;
                }
            }

            resize(currentMaxCapacity * DEFAULT_RESIZE_FACTOR);
        }
    }

    @Override
    public Optional<Long> get(int key) {
        int hashKey = getHashKey(key);

        for(int i = hashKey; i < currentMaxCapacity ; ++i) {
            if(data[i] == null) {
                return Optional.empty();
            }
            else if(data[i].getKey() == key) {
                Long value = data[i].getValue();
                return Optional.of(value);
            }
        }

        return Optional.empty();

    }


    /**
     * Increases the data field capacity, puts elements from old field to resized new
     */
    private void resize(int newCapacity) {
        MapEntry[] oldData = this.data;
        this.data = new MapEntry[newCapacity];
        this.currentMaxCapacity = newCapacity;
        this.presentElmCount = 0;

        for(MapEntry entry : oldData) {
            if(entry != null) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public int size() {
        return presentElmCount;
    }

    private int getHashKey(int key) {
        return key % currentMaxCapacity;
    }
}
