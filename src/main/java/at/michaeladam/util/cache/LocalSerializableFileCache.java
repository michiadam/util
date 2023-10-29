package at.michaeladam.util.cache;

import java.io.*;
import java.util.Optional;
import java.util.function.Supplier;

public class LocalSerializableFileCache<T extends Serializable>  extends LocalFileCache<T> {


    public LocalSerializableFileCache(String path, Class<T> type) {
        super(path, type);
    }


    public void writeObjectToFile(T value, String path) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public T readObjectFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return type.cast(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
