package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws Exception {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws Exception {
        checkConfigClass(configClass);
        Constructor<?> constructor = configClass.getConstructor();
        var object = constructor.newInstance();
        List<Method> methodWithAnnotation = getMethodsWithAnnotation(configClass);
        for (Method method : methodWithAnnotation) {
            Object component = method.invoke(object, getArgs(method));
            appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), component);
            appComponents.add(component);
        }
    }

    private Object[] getArgs(Method method) throws Exception {
        var parameters = method.getParameterTypes();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            args[i] = getAppComponent(parameters[i]);
        }
        return args;
    }

    private List<Method> getMethodsWithAnnotation(Class<?> configClass) {
        List<Method> methodWithAnnotation = new ArrayList<>();
        var methods = configClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AppComponent.class)) {
                methodWithAnnotation.add(method);
            }
        }
        methodWithAnnotation.sort((o1, o2) ->
                (o1.getAnnotation(AppComponent.class).order() - o2.getAnnotation(AppComponent.class).order()));
        return methodWithAnnotation;
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) throws Exception {
        int countComponent = 0;
        int indexComponent = 0;
        for (Object appComponent : appComponents) {
            if (componentClass.isAssignableFrom(appComponent.getClass())) {
                countComponent++;
                indexComponent = appComponents.indexOf(appComponent);

            }
        }
        if (countComponent == 0 || countComponent > 1) {
            String message = countComponent == 0 ? "Компонента не найдена" : "Найдено несколько компонентов одного класса";
            throw new Exception(message);
        }
        return (C) appComponents.get(indexComponent);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
