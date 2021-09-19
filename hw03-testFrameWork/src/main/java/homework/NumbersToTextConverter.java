package homework;



public class NumbersToTextConverter {
    private NumberClassConverter numberClassConverter;

    public NumbersToTextConverter(NumberClassConverter numberClassConverter) {
        this.numberClassConverter = numberClassConverter;
    }

    public String getTextNumberView(String Number) throws NumberFormatException{
        int length=Number.length();
        if (length%3==1) Number="00"+Number;
        if (length%3==2) Number="0"+Number;
        int countClass=Number.length()/3;
        String TextNumber="";
        while (countClass>0){
             int[] classNumber=getClassNumber(Number,countClass);
             TextNumber=TextNumber+numberClassConverter.getNumberClassText(classNumber,countClass-1);
             countClass--;
        }
        return TextNumber;

    }
    private static int[] getIntNumberClass(String numberClass) throws NumberFormatException
    {
        char[] numberClassArray=numberClass.toCharArray();
        int[]   numberClassInt=new int[numberClass.length()];
        int count=0;
        for (char unit:numberClassArray){
                if(Character.isDigit(unit)){
                    numberClassInt[count]=Character.getNumericValue(unit);
                    count++;
                }else{
                    throw new NumberFormatException("Некорректный символ "+unit+" повторите ввод");
                }
        }
        return numberClassInt ;
    };
    public int[] getClassNumber(String number,int classIndex) throws NumberFormatException {
        int indexStrBegin=(number.length()/3-classIndex)*3;
        int indexStrEnd=(number.length()/3-classIndex)*3+3;
        String classNumberString=number.substring(indexStrBegin,indexStrEnd);
        try {
            return getIntNumberClass(classNumberString);
        }catch (NumberFormatException e){
            throw new NumberFormatException(e.getMessage());
        }
     }
}
