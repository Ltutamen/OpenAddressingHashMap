package ua.axiom.apply.model;

public class NoMapElementException extends LightweightRuntimeException {
    private final int key;

    public NoMapElementException(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "NoMapElementException{" +
                "key=" + key +
                '}';
    }
}
