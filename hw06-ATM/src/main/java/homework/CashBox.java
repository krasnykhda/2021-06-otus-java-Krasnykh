package homework;

import java.util.Objects;

public class CashBox {
    private final Integer numberNominal;
    private int balance;

    public CashBox(int numberNominal) {
        this.numberNominal = numberNominal;
    }

    public Integer getNominal() {
        return numberNominal;
    }

    public void addBanknotes(int addValue) {
        this.balance += addValue;
    }

    public int getBalance() {
        return balance;
    }

    public void getBanknotes(int getValue) {
        this.balance -= getValue;
    }
}
