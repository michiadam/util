package at.michaeladam.util.cache;

import java.io.*;
import java.util.Optional;
import java.util.function.Supplier;

public class LocalFileCache<T extends Serializable> {


    private Optional<T> cachedValue = Optional.empty();

    private final String path;

    private final Class<T> type;

    public LocalFileCache(String path, Class<T> type) {
        this.path = path;
        this.type = type;

        try {
            this.cachedValue = Optional.ofNullable(type.cast(readObjectFromFile()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object readObjectFromFile() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
             return type.cast(objectInputStream.readObject());
        }
    }

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
        if(cachedValue.isPresent()){
            writeObjectToFile(cachedValue.get(), path);
        }
    }



    private void setCachedValue(T value) throws IOException {
        this.cachedValue = Optional.of(value);
        writeObjectToFile(value, path);
    }


    private void writeObjectToFile(T value, String path) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(value);
        }
    }


}
