package homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class TestRunner {
    private static List getMethodsWithAnnotation(Class clazz,Class annotation){
        List<Method> methods=new ArrayList<Method>();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(annotation)) {
                methods.add(m);
            }
        }
        return methods;
    }
    public static void run(Class clazz) throws Exception {
        Constructor<?> constructor = clazz.getConstructor();
        int tests = 0,passed = 0;
        List<Method> beforeMethods=getMethodsWithAnnotation(clazz,Before.class);
        List<Method> afterMethods=getMethodsWithAnnotation(clazz,After.class);
        List<Method> testMethods=getMethodsWithAnnotation(clazz,Test.class);
        Object object=null;
        for(Method testMethod:testMethods){
            object = constructor.newInstance();
            tests++;
            try {
                for(Method beforeMethod:beforeMethods){
                    beforeMethod.setAccessible(true);
                    beforeMethod.invoke(object);
                }
                testMethod.setAccessible(true);
                testMethod.invoke(object);
                for(Method afterMethod:afterMethods){
                    afterMethod.setAccessible(true);
                    afterMethod.invoke(object);
                }
                System.out.println(testMethod + " passed" );
                passed++;
            } catch (Throwable exc) {
                System.out.println(testMethod + " failed "+exc.getCause() );
                for(Method afterMethod:afterMethods){
                    afterMethod.setAccessible(true);
                    afterMethod.invoke(object);
                }

            }
        }
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }

    public static void main(String[] args) {
        try {
            run(ClassTest.class);
        }catch (Exception e){

        }
    }
}
