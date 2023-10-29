package at.michaeladam.util.cache;

import java.io.*;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class LocalFileCache<T > {

    private Optional<T> cachedValue = Optional.empty();

    protected final String path;

    protected final Class<T> type;

    public LocalFileCache(String path, Class<T> type) {
        this.path = path;
        this.type = type;

        this.cachedValue = Optional.ofNullable((readObjectFromFile()));
    }

    public abstract T readObjectFromFile();
    public abstract void writeObjectToFile(T obj, String path);


    public Optional<T> getCachedValue() {
        return cachedValue;
    }


    public T getCachedValue(Supplier<T> supplier) throws IOException {
        if (cachedValue.isPresent()) {
            return cachedValue.get();
        }
        T value = supplier.get();
        if (value != null) {
            setCachedValue(value);
        }
        return cachedValue.get();
    }

    public void flush() throws IOException {
        cachedValue.ifPresent(t -> writeObjectToFile(t, path));
        //reread
        cachedValue = Optional.ofNullable(readObjectFromFile());
    }



    private void setCachedValue(T value) throws IOException {
        this.cachedValue = Optional.of(value);
        writeObjectToFile(value, path);
    }



}
