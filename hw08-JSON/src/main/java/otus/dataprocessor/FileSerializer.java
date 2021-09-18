package otus.dataprocessor;

import javax.json.Json;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class FileSerializer implements Serializer {
    String textFile;

    public FileSerializer(String fileName) {
        this.textFile = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        Iterator<Map.Entry<String, Double>> itr = data.entrySet().iterator();
        var jsonObjectBuilder = Json.createObjectBuilder();

        while (itr.hasNext()) {
            Map.Entry<String, Double> entry = itr.next();
            jsonObjectBuilder.add(entry.getKey(), entry.getValue());
        }
        var jsonObject = jsonObjectBuilder.build();
        try (var bufferedWriter = new BufferedWriter(new FileWriter(textFile))) {
            bufferedWriter.write(jsonObject.toString());
        }

    }
}
