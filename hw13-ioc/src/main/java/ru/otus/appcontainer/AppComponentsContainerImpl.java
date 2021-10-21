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
            addComponentOrThrowException(component);
        }
    }

    private void addComponentOrThrowException(Object component) throws Exception {
        for (Object appComponent : appComponents) {
            if (component.getClass().equals(appComponent.getClass())) {
                throw new Exception("В конфигурации присутствуют компоненты одного типа");
            }
        }
        appComponents.add(component);
    }

    private Object[] getArgs(Method method) {
        var parameters = method.getParameterTypes();
        return Arrays.stream(parameters).map(parameterType ->
                        getAppComponent(parameterType))
                .toArray();
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
        for(Object appComponent:appComponents){
            if(componentClass.isAssignableFrom(appComponent.getClass())){
                return (C)appComponent;
            }
        }
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
