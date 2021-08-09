package homework;

public interface TestLoggerInterface {
    @Log
    public void calculate(int param);
    public void calculate(int param,int param2);
    @Log
    public void calculate(int param,int param2,int param3);


}
