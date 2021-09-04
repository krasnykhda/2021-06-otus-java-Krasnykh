package homework;

import java.util.Objects;

public class CashBox {
    private final Nominals nominal;
    private int balance;

    public CashBox(Nominals nominal) {
        this.nominal = nominal;
    }

    public Nominals getNominal() {
        return nominal;
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
