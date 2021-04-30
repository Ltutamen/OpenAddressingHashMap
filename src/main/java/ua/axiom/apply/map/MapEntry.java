package ua.axiom.apply.map;

public class MapEntry {
    private final int key;
    private long value;

    public MapEntry(int key, long value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
