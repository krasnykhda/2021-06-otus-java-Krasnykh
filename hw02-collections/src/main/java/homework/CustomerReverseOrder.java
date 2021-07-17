package homework;


import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.TreeMap;

public class CustomerReverseOrder {
    private final Deque<Customer> stack = new ArrayDeque<>();

    public void add(Customer customer) {
        stack.push(customer);
    }

    public Customer take() {
        return stack.pop() ;
    }
}
