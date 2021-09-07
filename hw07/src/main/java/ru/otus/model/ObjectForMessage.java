package ru.otus.model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public ObjectForMessage() {
    }

    public ObjectForMessage(ObjectForMessage msg) {
        this.data = new ArrayList<>(msg.getData());
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
