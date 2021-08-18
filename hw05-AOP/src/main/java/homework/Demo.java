package homework;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
public class Demo {
    public static void main(String[] args) {
        TestLoggerInterface testLoggerProxy=Ioc.createMyClass();
        testLoggerProxy.calculate(5);
        testLoggerProxy.calculate(5,5);
        testLoggerProxy.calculate(5,5,5);
    }

}
