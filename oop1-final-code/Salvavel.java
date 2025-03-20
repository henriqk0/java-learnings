import java.io.BufferedWriter;
import java.io.IOException;

public interface Salvavel {
    public abstract void salvarArq(BufferedWriter b) throws IOException, Exception;
}
