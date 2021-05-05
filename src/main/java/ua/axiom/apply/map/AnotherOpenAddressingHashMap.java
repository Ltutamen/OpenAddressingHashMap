package ua.axiom.apply.map;

import ua.axiom.apply.model.NoMapElementException;

public class AnotherOpenAddressingHashMap implements HashMap {
    private final static int DEFAULT_MAP_SIZE = 255;
    private final static int SCALE_FACTOR = 4;

    private int[] forHashIndexes;
    private long[] values;

    //  no "undefined" state for integer, use zero as undef, and this for 0
    private long forZeroKeyValue;
    private boolean isZeroKeySet;

    private int presentElementsCount;
    private int maxElements;

    public AnotherOpenAddressingHashMap() {
        this.forHashIndexes = new int[DEFAULT_MAP_SIZE];
        this.values = new long[DEFAULT_MAP_SIZE];
        this.presentElementsCount = 0;
        this.maxElements = DEFAULT_MAP_SIZE;
    }

    @Override
    public boolean put(int key, long value) {
        if(key == 0) {
            this.forZeroKeyValue = value;
            boolean flag = isZeroKeySet;
            this.isZeroKeySet = true;

            this.presentElementsCount += flag ? 0 : 1;

            return flag;
        }

        while (true) {
            int hashCode = getHashCode(key);

            for (int i = hashCode ; i < maxElements ; ++i) {
                if(forHashIndexes[i] == 0) {
                    //  add new
                    forHashIndexes[i] = key;
                    values[i] = value;

                    this.presentElementsCount++;
                    return false;
                } else if(forHashIndexes[i] == key) {
                    //  replace
                    values[i] = value;
                    return true;
                }
            }

            resize(maxElements * SCALE_FACTOR);

        }
    }

    @Override
    public long get(int key) throws NoMapElementException {
        if(key == 0) {
            return forZeroKeyValue;
        }

        int keyHash = getHashCode(key);

        for(int i = keyHash ; i < maxElements ; ++i) {
            if(forHashIndexes[i] == key) {
                return values[i];
            }
        }
        throw new NoMapElementException(key);
    }

    @Override
    public int size() {
        return presentElementsCount;
    }

    /**
     *
     * @param forKey
     * @return hashCode, NOT EQUALS TO 0!!
     */
    private int getHashCode(int forKey) {
        int result = forKey % maxElements;
        return result == 0 ? ++result : result;
    }

    private void resize(int newSize) {
        int[] oldForHashIndexes = this.forHashIndexes;
        long[] oldValues = this.values;

        this.forHashIndexes = new int[newSize];
        this.values = new long[newSize];
        this.maxElements = newSize;
        this.presentElementsCount = 0;

        for(int i = 0; i < oldForHashIndexes.length ; ++i) {
            if(oldForHashIndexes[i] != 0) {
                int key = oldForHashIndexes[i];
                long value = oldValues[i];
                put(key, value);
            }
        }

        //  and zero elms!
        if(isZeroKeySet) {
            this.isZeroKeySet = false;
            put(0, forZeroKeyValue);
        }
    }
}
