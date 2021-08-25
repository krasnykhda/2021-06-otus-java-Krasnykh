package homework;

public class Runner {
    public static void main(String[] args) {
        Nominals[] cellsNom = {Nominals.ТЫСЯЧА, Nominals.ДВЕ_ТЫСЯЧИ, Nominals.ПЯТЬ_ТЫСЯЧ, Nominals.ПЯТЬСОТ};
        Atm atm = new Atm(cellsNom);
        atm.addMoney(Nominals.ПЯТЬ_ТЫСЯЧ, 30);
        atm.addMoney(Nominals.ДВЕ_ТЫСЯЧИ, 10);
        atm.addMoney(Nominals.ПЯТЬСОТ, 10);
        atm.getMoney(125000);
    }
}
