package homework;

import java.util.*;

public class Atm {
    private final Map<Nominals, CashBox> cashBoxes = new TreeMap<>((o1, o2) -> o2.getNumberNominal() - o1.getNumberNominal());
    private final AtmController atmController = new AtmController();

    public Atm(Nominals[] cellsNominals) {
        for (var nominal : cellsNominals) {
            cashBoxes.put(nominal, new CashBox(nominal));
        }
    }

    public void addMoney(Nominals nominal, int value) {
        try {
            atmController.addMoney(nominal, value, cashBoxes);
        } catch (CellsNotFoundException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void getMoney(int value) {
        try {
            atmController.giveMoney(value, cashBoxes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public int getAtmBalance() {
        return atmController.getAtmBalance();
    }

    public int getCellBalance(Nominals nominal) {
        return cashBoxes.get(nominal).getBalance();
    }

}
