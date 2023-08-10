import java.io.IOException;
public interface Writable {
    void write(String name) throws RuntimeException, IOException;
}
