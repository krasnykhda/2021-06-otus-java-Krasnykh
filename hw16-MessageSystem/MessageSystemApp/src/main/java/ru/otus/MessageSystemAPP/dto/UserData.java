package ru.otus.MessageSystemAPP.dto;

import ru.otus.MessageSystemAPP.crm.model.Client;
import ru.otus.client.ResultDataType;

import java.util.List;

public class UserData implements ResultDataType {
    private final long userId;
    private final List<Client> data;

    public UserData(long userId) {
        this.userId = userId;
        this.data = null;
    }

    public UserData(long userId, List<Client> data) {
        this.userId = userId;
        this.data = data;
    }

    public long getUserId() {
        return userId;
    }

    public List<Client> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", data='" + data + '\'' +
                '}';
    }
}
