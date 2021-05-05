package ua.axiom.apply.model;


public class LightweightRuntimeException extends RuntimeException {
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
