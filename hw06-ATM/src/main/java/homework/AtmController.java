package homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AtmController {
    private int atmBalance;

    public void giveMoney(int value, Map<Integer, CashBox> cashBoxes) {
        try {
            executeOperation(value, cashBoxes, true);
            executeOperation(value, cashBoxes, false);
        } catch (MoneyNotFoundException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public int getAtmBalance() {
        return atmBalance;
    }

    private void executeOperation(int value, Map<Integer, CashBox> cashBoxes, boolean checkingThePossibility) throws MoneyNotFoundException {
        if (checkingThePossibility && value > atmBalance) {
            throw new MoneyNotFoundException("В банкомате недостаточно средств");
        }
        Iterator<Map.Entry<Integer, CashBox>> itr = cashBoxes.entrySet().iterator();
        while (itr.hasNext() && value > 0) {
            Map.Entry<Integer, CashBox> entry = itr.next();
            Integer nominal = entry.getKey();
            int numberOfBanknotes = value / nominal;
            CashBox cell = entry.getValue();
            Integer cellBalance = cell.getBalance();
            int amountDebited;
            if (cellBalance > 0) {
                if (numberOfBanknotes >= cellBalance) {
                    amountDebited = cellBalance;
                } else {
                    amountDebited = numberOfBanknotes;
                }
                if (!checkingThePossibility) {
                    cell.getBanknotes(amountDebited);
                    this.atmBalance -= amountDebited * nominal;
                }
                value -= amountDebited * nominal;
            }
        }
        if (checkingThePossibility && value > 0) {
            throw new MoneyNotFoundException("Нет возможноти набрать указанную сумму!");
        }
    }

    public void addMoney(Integer nominal, int value, Map<Integer, CashBox> cashBoxes) throws CellsNotFoundException {
        CashBox cell = cashBoxes.get(nominal);
        if (cell == null) {
            throw new CellsNotFoundException("В банкомате нет ячейки для данного номинала " + nominal.toString());
        }
        this.atmBalance += nominal * value;
        cell.addBanknotes(value);
    }
}
