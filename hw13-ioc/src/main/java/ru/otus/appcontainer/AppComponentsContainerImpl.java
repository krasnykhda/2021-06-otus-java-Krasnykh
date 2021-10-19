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
            appComponentsByName.put(component.getClass().getSimpleName(), component);
            appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), component);
            if (method.getReturnType().isInterface()) {
                addComponentByInterfaceOrThrowException(method.getReturnType().getSimpleName(), component);
            } else {
                for (Class<?> interfaceName : method.getReturnType().getInterfaces()) {
                    addComponentByInterfaceOrThrowException(interfaceName.getSimpleName(), component);
                }
            }
        }
    }

    private void addComponentByInterfaceOrThrowException(String typeName, Object component) throws Exception {
        if (appComponents.indexOf(typeName) > -1) {
            throw new Exception("В конфигурации найдено несколько реализаций одного интерфейса");
        }
        appComponents.add(typeName);
        appComponentsByName.put(typeName, component);

    }

    private Object[] getArgs(Method method) {
        var parameters = method.getParameterTypes();
        return Arrays.stream(parameters).map(parameterType ->
                        appComponentsByName.get(parameterType.getSimpleName()))
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
        var component = appComponentsByName.get(componentClass.getSimpleName());
        return (C) component;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
