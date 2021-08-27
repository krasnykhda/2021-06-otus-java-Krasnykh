package homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class AtmController {
    private int atmBalance;

    public void giveMoney(int value, Map<Nominals, CashBox> cashBoxes) {
        Map<Nominals, Integer> numberOfDebitedBanknoted = getNumberOfDebitedBanknoted(value, cashBoxes);
        executeOperation(cashBoxes, numberOfDebitedBanknoted);
    }

    public int getAtmBalance() {
        return atmBalance;
    }

    private Map<Nominals, Integer> getNumberOfDebitedBanknoted(int value, Map<Nominals, CashBox> cashBoxes) {
        if (value > atmBalance) {
            throw new MoneyNotFoundException("В банкомате недостаточно средств");
        }
        Map<Nominals, Integer> numberOfDebitedBanknoted = new TreeMap<>();
        Iterator<Map.Entry<Nominals, CashBox>> itr = cashBoxes.entrySet().iterator();
        while (itr.hasNext() && value > 0) {
            Map.Entry<Nominals, CashBox> entry = itr.next();
            Nominals nominal = entry.getKey();
            int numberOfBanknotes = value / nominal.getNumberNominal();
            CashBox cell = entry.getValue();
            Integer cellBalance = cell.getBalance();
            if (cellBalance > 0 && numberOfBanknotes > 0) {
                int amountDebited = (numberOfBanknotes >= cellBalance) ? cellBalance : numberOfBanknotes;
                numberOfDebitedBanknoted.put(nominal, amountDebited);
                value -= amountDebited * nominal.getNumberNominal();
            }
        }
        if (value > 0) {
            throw new MoneyNotFoundException("Нет возможноти набрать указанную сумму!");
        }
        return numberOfDebitedBanknoted;
    }

    private void executeOperation(Map<Nominals, CashBox> cashBoxes, Map<Nominals, Integer> numberOfDebitedBanknoted) {
        Iterator<Map.Entry<Nominals, Integer>> itr = numberOfDebitedBanknoted.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Nominals, Integer> entry = itr.next();
            Nominals nominal = entry.getKey();
            CashBox cell = cashBoxes.get(nominal);
            cell.getBanknotes(entry.getValue());
            this.atmBalance -= entry.getValue() * nominal.getNumberNominal();
        }
    }

    public void addMoney(Nominals nominal, int value, Map<Nominals, CashBox> cashBoxes) throws CellsNotFoundException {
        CashBox cell = cashBoxes.get(nominal);
        if (cell == null) {
            throw new CellsNotFoundException("В банкомате нет ячейки для данного номинала " + nominal.toString());
        }
        this.atmBalance += nominal.getNumberNominal() * value;
        cell.addBanknotes(value);
    }
}
