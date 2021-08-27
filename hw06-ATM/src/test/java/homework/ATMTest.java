package homework;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class ATMTest {
    private Atm atm;

    @BeforeEach
    void setUp() {
        Nominals[] cellsNominals = {Nominals.THOUSAND, Nominals.TWO_THOUSAND, Nominals.FIVE_THOUSAND, Nominals.FIVE_HUNDRED};
        atm = new Atm(cellsNominals);
    }

    @Test
    @DisplayName("Должен вернуть правильное значение баланса банкомата")
    void test1() {
        atm.addMoney(Nominals.FIVE_THOUSAND, 30);
        atm.addMoney(Nominals.TWO_THOUSAND, 10);
        atm.addMoney(Nominals.FIVE_HUNDRED, 10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getAtmBalance()).isEqualTo(49500);
    }

    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 5000")
    @Test
    void test2() {
        atm.addMoney(Nominals.FIVE_THOUSAND, 30);
        atm.addMoney(Nominals.TWO_THOUSAND, 10);
        atm.addMoney(Nominals.FIVE_HUNDRED, 10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getCellBalance(Nominals.FIVE_THOUSAND)).isEqualTo(5);
    }

    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 500")
    @Test
    void test3() {
        atm.addMoney(Nominals.FIVE_THOUSAND, 30);
        atm.addMoney(Nominals.TWO_THOUSAND, 10);
        atm.addMoney(Nominals.FIVE_HUNDRED, 10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getCellBalance(Nominals.FIVE_HUNDRED)).isEqualTo(9);
    }

    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 500")
    @Test
    void test4() {
        atm.addMoney(Nominals.FIVE_THOUSAND, 30);
        atm.addMoney(Nominals.TWO_THOUSAND, 10);
        atm.addMoney(Nominals.FIVE_HUNDRED, 10);
        atm.getMoney(195500);
    }

}
