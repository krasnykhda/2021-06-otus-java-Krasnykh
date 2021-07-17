package homework;


import java.util.Comparator;
import java.util.*;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    private final NavigableMap<Customer, String> treeMap = new TreeMap<>((o1, o2) -> (int) (o1.getScores() - o2.getScores()));
    public NavigableMap<Customer, String> copyTreeMap(){
        NavigableMap<Customer, String> treeMapCopy = new TreeMap<>((o1, o2) -> (int) (o1.getScores() - o2.getScores()));
        for(Map.Entry<Customer, String> entry : treeMap.entrySet()) {
            treeMapCopy.put(new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores()), entry.getValue());
        }
        return treeMapCopy;
    }
    public Map.Entry<Customer, String> getSmallest() {
        NavigableMap<Customer, String> treeMapCopy = copyTreeMap();
        return treeMapCopy.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        NavigableMap<Customer, String> treeMapCopy = copyTreeMap();
        return treeMapCopy.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer,data);
    }
}
