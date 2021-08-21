package homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Controller {
    private int atmBalance;
    public void giveMoney(int value,Map<Integer,CashBox> cashBoxes){
        if(executeOperation(value,cashBoxes,true)){
            executeOperation(value,cashBoxes,false);
        }
    }

    public int getAtmBalance() {
        return atmBalance;
    }

    private boolean executeOperation(int value, Map<Integer,CashBox> cashBoxes, boolean checkingThePossibility) {
        if(checkingThePossibility && value>atmBalance){
            System.out.println("В банкомате недостаточно средств");
            return false;
        }
        Iterator<Map.Entry<Integer, CashBox>> itr = cashBoxes.entrySet().iterator();
        while(itr.hasNext()&&value>0) {
            Map.Entry<Integer, CashBox> entry = itr.next();
            Integer nominal = entry.getKey();
            int valueOfBanknotes=value/nominal;
            CashBox cell = entry.getValue();
            Integer cellBalance= cell.getBalance();
            if(cellBalance>0){
                if(valueOfBanknotes>=cellBalance){
                    if(!checkingThePossibility) {
                        cell.getBanknotes(cellBalance);
                        this.atmBalance -= cellBalance * nominal;
                    }
                    value-=cellBalance*nominal;
                }else {
                    if(!checkingThePossibility) {
                        cell.getBanknotes(valueOfBanknotes);
                        this.atmBalance -= valueOfBanknotes * nominal;
                    }
                    value-=valueOfBanknotes*nominal;
                }
            }
        }
        if(checkingThePossibility&&value>0){
            System.out.println("Нет возможноти набрать указанную сумму!");
            return false;
        }
        return true;
    }
    public void addMoney(Integer nominal,int value,Map<Integer,CashBox> cashBoxes) {
        CashBox cell=cashBoxes.get(nominal);
        if(cell==null){
            System.out.println("В банкомате нет ячейки для данного номинала");
            return;
        }
        this.atmBalance+=nominal*value;
        cell.addBanknotes(value);
    }
}
