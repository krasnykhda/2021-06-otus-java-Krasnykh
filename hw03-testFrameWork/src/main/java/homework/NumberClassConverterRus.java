package homework;



public class NumberClassConverterRus implements NumberClassConverter {
    private static final String[][] className={
            {"","",""},
            {"тысяча","тысячи","тысяч"},
            {"миллион","миллиона","миллионов"},
            {"миллиард","миллиарда","миллиардов"},
            {"триллион","триллиона","триллионов"},
            {"квадриллион","квадриллиона","квадриллионов"},
            {"квинтиллион","квинтиллиона","квинтиллионов"},
            {"секстиллион","секстиллиона","секстиллионов"},
            {"септиллион","септиллиона","септиллионв"},
            {"октиллион","октиллиона","октиллионов"},
            {"нониллион","нониллиона","нониллионов"},
            {"дециллион","дециллиона","дециллионов"},
            {"ундециллион","ундециллиона","ундециллионов"},

        };
    private static final String[][] textNumber={
            {"","","","",""},
            {" сто",""," один"," одна"," одиннадцать"," десять"},
            {" двести"," двадцать"," два"," две"," двенадцать",""},
            {" триста"," тридцать"," три"," три","тринадцать",""},
            {" четыреста"," сорок"," четыре"," четыре"," четырнадцать"},
            {" пятьсот"," пятьдесят"," пять"," пять"," пятнадцать",""},
            {" шестьсот"," шестьдесят"," шесть"," шесть"," шестнадцать",""},
            {" семьсот"," семьдесят"," семь"," семь"," семнадцать",""},
            {" восемьсот"," восемьдесят"," восемь"," восемь"," восемнадцать",""},
            {" девятьсот"," девяносто"," девять"," девять"," девятнадцать",""}

    };
    private int getClassNamePosition(int[] numberClass)
    {
       if(numberClass[2]==1&&(numberClass[1]==0)) return 0;
       if(numberClass[2]>1&&numberClass[2]<5&&(numberClass[1]==0||numberClass[1]>1)) return 1;
       if(numberClass[2]>=5||numberClass[2]==0||numberClass[1]==1) return 2;
       return 0;
    };
    private int getTensPosition(int[] numberClass,int classIndex)
    {
        int tens,units;
        tens=numberClass[1];
        units=numberClass[2];
        if(tens==0&&units==0||tens==0&&units>0&&classIndex==1 ||tens==0&&units>0&&classIndex>1 ){
            return 0;
        }
        if(tens==1&&units==0){
            return 5;
        }
        if(tens==1&&units>0||tens>1||classIndex==1&&tens==1){
            return 1;
        }
        if(tens==1){
            return 4;
        }
        return 0;
    };
    private int getUnitsPosition(int[] numberClass,int classIndex)
    {
        int tens,units;
        tens=numberClass[1];
        units=numberClass[2];
        if(tens==0&&units==0||tens==1&&units==0){
            return 0;
        }
        if(tens==1&&units>0||classIndex==1&&tens==1){
            return 4;
        }
        if(tens==0&&units>0&&classIndex==1||classIndex==1){
            return 3;
        }
        if(tens==0&&units>0&&classIndex>1||classIndex>1){
            return 2;
        }
        if(tens==1){
            return 1;
        }
        return 2;
    };
    private String addCurrencyToEndNumber(int[] numberClass)
    {
        int unit=numberClass[2];
        if(unit==0) return "рублей";
        if(unit==1) return "рубль";
        if(unit>1&&unit<5) return "рубля";
        if(unit>=5) return "рублей";
        return "";
    };
    @Override
    public String getNumberClassText(int[] numberClass,int classIndex) throws NumberFormatException {
        if(classIndex>12)
        {
            throw new NumberFormatException("Превышена разрядность числа");
        };
        int tensPosition=getTensPosition(numberClass,classIndex);
        int unitPosition=getUnitsPosition(numberClass,classIndex);
        String endNumber=(classIndex>=0&&(numberClass[0]!=0||numberClass[1]!=0||numberClass[2]!=0))?className[classIndex][getClassNamePosition(numberClass)]:"";
        if (classIndex==0)
        {
           endNumber=endNumber+addCurrencyToEndNumber(numberClass);
        }
        return textNumber[numberClass[0]][0]+textNumber[numberClass[1]][tensPosition]+textNumber[numberClass[2]][unitPosition]+" "+endNumber;
    }
}
