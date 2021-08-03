package homework;

import java.lang.reflect.InvocationTargetException;

public class ClassTest {
    private  NumberClassConverter numberClassConverter;
    private  NumbersToTextConverter numbersToTextConverter;
    @Before
    public void setUp(){
         numberClassConverter=new NumberClassConverterRus();
         numbersToTextConverter=new NumbersToTextConverter(numberClassConverter);
    }
    @After
    public void after(){
        System.out.println("Завершающие действия после теста");
    }
    @Test
    private void testMethod1()
    {
        System.out.println("Начало тестового метода 1");
        Assertions.assertEquals(" 1сто двадцать три рубля",numbersToTextConverter.getTextNumberView("123"));
    }
    @Test
    private void testMethod2()
    {
        System.out.println("Начало тестового метода 2");
        Assertions.assertEquals(" одна тысяча двести тридцать пять рублей",numbersToTextConverter.getTextNumberView("1235"));
        System.out.println("2");
    }
    @Test
    private void testMethod3()
    {
        System.out.println("Начало тестового метода 3");
        Assertions.assertEquals(" один рубль",numbersToTextConverter.getTextNumberView("1"));
    }
    @Test
    private void testMethod4()
    {
        System.out.println("Начало тестового метода 4");
        Assertions.assertThrows(IllegalArgumentException.class,() ->numbersToTextConverter.getTextNumberView("f"));
    }
}
