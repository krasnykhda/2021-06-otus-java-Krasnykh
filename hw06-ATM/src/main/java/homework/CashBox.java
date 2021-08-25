package homework;

import java.util.Objects;

public class CashBox {
    private final Integer numberNominal;
    private int balance;

    public static Integer getNumberNominal(Nominals nominal) {
        Integer numberNominal = 0;
        switch (nominal) {
            case ПЯТЬ_ТЫСЯЧ -> numberNominal = 5000;
            case ДВЕ_ТЫСЯЧИ -> numberNominal = 2000;
            case ПЯТЬСОТ -> numberNominal = 500;
            case ТЫСЯЧА -> numberNominal = 1000;
        }
        return numberNominal;
    }

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
