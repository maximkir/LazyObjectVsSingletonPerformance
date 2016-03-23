package util;

import java.util.Objects;
import java.util.function.Supplier;

public final class Lazy<T> {

    private  T value;

    public T getOrCompute(Supplier<T> supplier){
        final T result = value;
        return result == null ? maybeCompute(supplier) : result;
    }

    private synchronized T maybeCompute(Supplier<T> supplier) {
        if (value == null){
            value = Objects.requireNonNull(supplier.get());
        }
        return value;
    }
}
