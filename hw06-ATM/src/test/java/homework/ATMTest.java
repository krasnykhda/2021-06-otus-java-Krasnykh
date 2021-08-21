package homework;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
public class ATMTest {
    private ATM atm;
    @BeforeEach
    void setUp() {
        Integer[]cellsNominals={1000,2000,5000,500};
        atm=new ATM(cellsNominals);
    }
    @Test
    @DisplayName("Должен вернуть правильное значение баланса банкомата")
    void test1(){
        atm.addMoney(5000,30);
        atm.addMoney(2000,10);
        atm.addMoney(500,10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getAtmBalance()).isEqualTo(49500);
    }
    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 5000")
    @Test
    void test2(){
        atm.addMoney(5000,30);
        atm.addMoney(2000,10);
        atm.addMoney(500,10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getCellBalance(5000)).isEqualTo(5);
    }
    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 500")
    @Test
    void test3(){
        atm.addMoney(5000,30);
        atm.addMoney(2000,10);
        atm.addMoney(500,10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getCellBalance(500)).isEqualTo(9);
    }
    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 500")
    @Test
    void test4(){
        atm.addMoney(5000,30);
        atm.addMoney(2000,10);
        atm.addMoney(500,10);
        atm.getMoney(195500);
        Assertions.assertThat(atm.getCellBalance(500)).isEqualTo(10);
    }

}
