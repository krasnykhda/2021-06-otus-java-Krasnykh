package homework;

public class Runner {
    public static void main(String[] args) {
        Nominals[] cellsNom = {Nominals.THOUSAND, Nominals.TWO_THOUSAND, Nominals.FIVE_THOUSAND, Nominals.FIVE_HUNDRED};
        Atm atm = new Atm(cellsNom);
        atm.addMoney(Nominals.FIVE_THOUSAND, 30);
        atm.addMoney(Nominals.TWO_THOUSAND, 10);
        atm.addMoney(Nominals.FIVE_HUNDRED, 10);
        atm.getMoney(127100);
        System.out.println(atm.getAtmBalance());
    }
}
