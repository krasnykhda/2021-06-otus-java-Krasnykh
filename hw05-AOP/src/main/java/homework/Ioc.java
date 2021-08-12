package homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

class Ioc {

    private Ioc() {
    }

    static TestLoggerInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogger());
        return (TestLoggerInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggerInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggerInterface myClass;
        Set<Method> methodsWithLog=new HashSet<>();
        DemoInvocationHandler(TestLoggerInterface myClass) {
            this.myClass = myClass;
            for(var method: TestLoggerInterface.class.getDeclaredMethods()){
                if(method.isAnnotationPresent(Log.class)){
                    methodsWithLog.add(method);
                }
            }
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methodsWithLog.contains(method)){
               System.out.println("executed method: " + method.getName()+" param:"+ Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
