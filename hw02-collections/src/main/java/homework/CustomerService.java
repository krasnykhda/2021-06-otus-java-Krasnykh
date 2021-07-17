package homework;


import java.util.Comparator;
import java.util.*;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    private final NavigableMap<Customer, String> treeMap = new TreeMap<>((o1, o2) -> (int) (o1.getScores() - o2.getScores()));
    public NavigableMap<Customer, String> copyTreeMap(Map.Entry<Customer, String> entry){
        NavigableMap<Customer, String> treeMapCopy = new TreeMap<>((o1, o2) -> (int) (o1.getScores() - o2.getScores()));
        if(entry!=null){
            treeMapCopy.put(new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores()), entry.getValue());
        }
        return treeMapCopy;
    }
    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = treeMap.firstEntry();
        NavigableMap<Customer, String> treeMapCopy = copyTreeMap(entry);
        return treeMapCopy.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = treeMap.higherEntry(customer);
        NavigableMap<Customer, String> treeMapCopy = copyTreeMap(entry);
        return treeMapCopy.firstEntry();
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer,data);
    }
}
