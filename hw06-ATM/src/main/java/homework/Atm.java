package homework;

import java.util.*;

public class Atm {
    private final Map<Integer, CashBox> cashBoxes = new TreeMap<>((o1, o2) -> o2 - o1);
    private final AtmController atmController = new AtmController();

    public Atm(Nominals[] cellsNominals) {
        for (var nominal : cellsNominals) {
            Integer numberNominal = nominal.getNumberNominal();
            cashBoxes.put(numberNominal, new CashBox(numberNominal));
        }
    }

    public void addMoney(Nominals nominal, int value) {
        try {
            atmController.addMoney(nominal.getNumberNominal(), value, cashBoxes);
        } catch (CellsNotFoundException exception) {
            System.err.println(exception.getMessage());
        }

    }

    public void getMoney(int value) {
        atmController.giveMoney(value, cashBoxes);
    }

    public int getAtmBalance() {
        return atmController.getAtmBalance();
    }

    public int getCellBalance(Integer nominal) {
        return cashBoxes.get(nominal).getBalance();
    }

}
