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
        Nominals[] cellsNominals = {Nominals.ТЫСЯЧА, Nominals.ДВЕ_ТЫСЯЧИ, Nominals.ПЯТЬ_ТЫСЯЧ, Nominals.ПЯТЬСОТ};
        atm = new Atm(cellsNominals);
    }

    @Test
    @DisplayName("Должен вернуть правильное значение баланса банкомата")
    void test1() {
        atm.addMoney(Nominals.ПЯТЬ_ТЫСЯЧ, 30);
        atm.addMoney(Nominals.ДВЕ_ТЫСЯЧИ, 10);
        atm.addMoney(Nominals.ПЯТЬСОТ, 10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getAtmBalance()).isEqualTo(49500);
    }

    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 5000")
    @Test
    void test2() {
        atm.addMoney(Nominals.ПЯТЬ_ТЫСЯЧ, 30);
        atm.addMoney(Nominals.ДВЕ_ТЫСЯЧИ, 10);
        atm.addMoney(Nominals.ПЯТЬСОТ, 10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getCellBalance(5000)).isEqualTo(5);
    }

    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 500")
    @Test
    void test3() {
        atm.addMoney(Nominals.ПЯТЬ_ТЫСЯЧ, 30);
        atm.addMoney(Nominals.ДВЕ_ТЫСЯЧИ, 10);
        atm.addMoney(Nominals.ПЯТЬСОТ, 10);
        atm.getMoney(125500);
        Assertions.assertThat(atm.getCellBalance(500)).isEqualTo(9);
    }

    @DisplayName("Должен вернуть правильное значение баланса ячейки с номиналом 500")
    @Test
    void test4() {
        atm.addMoney(Nominals.ПЯТЬ_ТЫСЯЧ, 30);
        atm.addMoney(Nominals.ДВЕ_ТЫСЯЧИ, 10);
        atm.addMoney(Nominals.ПЯТЬСОТ, 10);
        atm.getMoney(195500);
        Assertions.assertThat(atm.getCellBalance(500)).isEqualTo(10);
    }

}
