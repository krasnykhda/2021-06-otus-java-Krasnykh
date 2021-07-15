package homework;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomerTest {

    // Все тесты должны проходить, менять тесты не надо.

    @Test
    @DisplayName("Объект Customer как ключ в карте")
    void customerAsKeyTest() {
        final long customerId = 1L;
        Customer customer = new Customer(customerId, "Ivan", 233);
        Map<Customer, String> map = new HashMap<>();

        String expectedData = "data";
        map.put(customer, expectedData);

        long newScore = customer.getScores() + 10;
        String factData = map.get(new Customer(customerId, "IvanChangedName", newScore));

        assertThat(factData).isEqualTo(expectedData);

        long newScoreSecond = customer.getScores() + 20;
        customer.setScores(newScoreSecond);
        String factDataSecond = map.get(customer);

        assertThat(factDataSecond).isEqualTo(expectedData);
    }

    @Test
    @DisplayName("Сортировка по полю score, итерация по возрастанию")
    void scoreSortingTest() {
        Customer customer1 = new Customer(1, "Ivan", 233);
        Customer customer2 = new Customer(2, "Petr", 11);
        Customer customer3 = new Customer(3, "Pavel", 888);

        CustomerService customerService = new CustomerService();
        customerService.add(customer1, "Data1");
        customerService.add(customer2, "Data2");
        customerService.add(customer3, "Data3");

        Map.Entry<Customer, String> smallestScore = customerService.getSmallest();
        assertThat(smallestScore.getKey()).isEqualTo(customer2);


        Map.Entry<Customer, String> middleScore = customerService.getNext(new Customer(10, "Key", 20));
        //Следующий scores после 20 - 233, поэтому вернется пара Customer 1 - "Data1"

        assertThat(middleScore.getKey()).isEqualTo(customer1);
        middleScore.getKey().setScores(10000);//Тут у customer1 установили scores в 10000 и поменяли имя
        middleScore.getKey().setName("Vasy");

         Map.Entry<Customer, String> biggestScore = customerService.getNext(customer1);
        //Тут взвращается null, т.к 10000 теперь максимальное значение в коллекции - следующего элемента нет

        assertThat(biggestScore.getKey()).isEqualTo(customer3);
        //null сравнивается с customer3 - тест не проходит. Правильно ли такое сравнение?

        Map.Entry<Customer, String> notExists = customerService.getNext(new Customer(100, "Not exists", 20000));

        assertThat(notExists).isNull();

    }

    @Test
    @DisplayName("Модификация коллекции")
    void mutationTest() {
        Customer customer1 = new Customer(1, "Ivan", 233);
        Customer customer2 = new Customer(2, "Petr", 11);
        Customer customer3 = new Customer(3, "Pavel", 888);

        CustomerService customerService = new CustomerService();
        customerService.add(customer1, "Data1");
        customerService.add(new Customer(customer2.getId(), customer2.getName(), customer2.getScores()), "Data2");
        customerService.add(customer3, "Data3");

        Map.Entry<Customer, String> smallestScore = customerService.getSmallest();
        //тут вернулсь пара с полями ключа как в Customer2, но это не Customer2.
        smallestScore.getKey().setName("Vasyl");
        //У ключа данной пары поменяли имя

        assertThat(customerService.getSmallest().getKey().getName()).isEqualTo(customer2.getName());
        //тест хочет чтобы Vasyl был равен  Petr, но разве это возможно?
    }

    @Test
    @DisplayName("Возвращание в обратном порядке")
    void reverseOrderTest() {

        Customer customer1 = new Customer(1, "Ivan", 233);
        Customer customer2 = new Customer(3, "Petr", 11);
        Customer customer3 = new Customer(2, "Pavel", 888);

        CustomerReverseOrder customerReverseOrder = new CustomerReverseOrder();
        customerReverseOrder.add(customer1);
        customerReverseOrder.add(customer2);
        customerReverseOrder.add(customer3);

        Customer customerLast = customerReverseOrder.take();

        assertThat(customerLast).usingRecursiveComparison().isEqualTo(customer3);

        Customer customerMiddle = customerReverseOrder.take();

        assertThat(customerMiddle).usingRecursiveComparison().isEqualTo(customer2);

        Customer customerFirst = customerReverseOrder.take();

        assertThat(customerFirst).usingRecursiveComparison().isEqualTo(customer1);
    }
}