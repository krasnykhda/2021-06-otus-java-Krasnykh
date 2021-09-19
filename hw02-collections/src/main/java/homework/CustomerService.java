package homework;


import java.util.Comparator;
import java.util.*;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    private final NavigableMap<Customer, String> treeMap = new TreeMap<>((o1, o2) -> (int) (o1.getScores() - o2.getScores()));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = treeMap.firstEntry();
        return entry == null ? null:new AbstractMap.SimpleImmutableEntry<>(new Customer(entry.getKey()), entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = treeMap.higherEntry(customer);
        return entry == null ? null:new AbstractMap.SimpleImmutableEntry<>(new Customer(entry.getKey()), entry.getValue());
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer,data);
    }
}
