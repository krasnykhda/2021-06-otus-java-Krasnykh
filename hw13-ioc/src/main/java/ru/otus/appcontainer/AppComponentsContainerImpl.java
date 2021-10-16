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
            List<Object> args = new ArrayList<>();
            var parameters = method.getParameterTypes();
            for (var parameter : parameters) {
                args.add(appComponentsByName.get(parameter.getSimpleName()));
            }
            Object obj = args.size() > 0 ? method.invoke(object, args.toArray()) : method.invoke(object);
            appComponentsByName.put(method.getReturnType().getSimpleName(), obj);
            appComponentsByName.put(obj.getClass().getSimpleName(), obj);
            appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), obj);
        }
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
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponentsByName.get(componentClass.getSimpleName());
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
