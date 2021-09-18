package otus.dataprocessor;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import otus.model.Measurement;
import otus.model.Measurement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class FileLoader implements Loader {
    String textFile;

    public FileLoader(String fileName) {
        this.textFile = fileName;
    }

    @Override
    public List<Measurement> load() throws IOException {
        try (var bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String line = bufferedReader.readLine();
            Type itemsListType = new TypeToken<List<Measurement>>() {
            }.getType();
            return new Gson().fromJson(line, itemsListType);
        }
    }
}
