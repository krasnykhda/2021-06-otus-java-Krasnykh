package otus.dataprocessor;

import otus.model.Measurement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        Map<String, Double> map = new TreeMap<>();
        for (Measurement item : data) {
            double acc = map.get(item.getName()) == null ? 0 : map.get(item.getName());
            map.put(item.getName(), acc + item.getValue());
        }
        return map;
    }
}
