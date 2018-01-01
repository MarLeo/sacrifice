package api;

import java.io.IOException;
import java.util.List;

public interface Components<T> {

    List<T> findAllComponents() throws IOException;

}
