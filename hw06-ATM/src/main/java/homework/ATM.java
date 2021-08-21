package homework;

import java.util.*;

public class ATM {
    private final Map<Integer,CashBox> cashBoxes=new TreeMap<>((o1, o2) -> (int) (o2-o1));
    private Controller controller=new Controller();

    public ATM(Integer[]cellsNominals) {
        for(var nominal:cellsNominals){
            cashBoxes.put(nominal, new CashBox(nominal));
        }
    }
    public void addMoney(Integer nominal,int value){
        controller.addMoney(nominal,value,cashBoxes);
    }
    public void getMoney(int value){
        controller.giveMoney(value,cashBoxes);
    }
    public int getAtmBalance(){
       return controller.getAtmBalance();
    }
    public int getCellBalance(Integer nominal){
        return cashBoxes.get(nominal).getBalance();
    }

}
