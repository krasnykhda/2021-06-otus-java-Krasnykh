package homework;

public class Runner {
    public static void main(String[] args) {
        Integer[]cellsNominals={1000,2000,5000,500};
        ATM atm=new ATM(cellsNominals);
        atm.addMoney(5000,30);
        atm.addMoney(2000,10);
        atm.addMoney(500,10);
        atm.getMoney(125500);
    }
}
