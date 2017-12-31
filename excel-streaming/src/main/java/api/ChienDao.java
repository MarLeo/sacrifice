package api;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

public interface ChienDao {

    List<Chien> findAllChiens() throws IOException, InvalidFormatException;
}
