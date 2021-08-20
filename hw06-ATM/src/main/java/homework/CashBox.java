package homework;

public class CashBox {
    private final Integer nominal;
    private int balance;
    public CashBox(int nominal) {
        this.nominal = nominal;
    }

    public Integer getNominal() {
        return nominal;
    }
    public void addBanknotes(int addValue){
        this.balance+=addValue;
    }
    public int getBalance() {
        return balance;
    }
    public void getBanknotes(int getValue){
        this.balance-=getValue;
    }
}
