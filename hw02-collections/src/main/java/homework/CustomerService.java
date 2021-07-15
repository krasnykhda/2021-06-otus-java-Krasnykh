package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    TreeMap<Customer, String> treeMap;


    public CustomerService() {
        this.treeMap =  new TreeMap<Customer, String>(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2)
            {
                return (int)(o1.getScores() - o2.getScores());
            }
        });
    }


    public Map.Entry<Customer, String> getSmallest() {
        return treeMap.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return treeMap.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer,data);
    }
}
