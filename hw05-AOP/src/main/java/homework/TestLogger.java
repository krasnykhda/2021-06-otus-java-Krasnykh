package homework;

public class TestLogger implements TestLoggerInterface {
    @Log
    public void calculate(int param){
        System.out.println(param);
    }
    @Log
    public void calculate(int param,int param2){
        System.out.println(param+param2);
    }
    public void calculate(int param,int param2,int param3){
        System.out.println(param+param2+param3);
    }
}
