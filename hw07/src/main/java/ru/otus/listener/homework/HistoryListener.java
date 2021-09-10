package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.*;

public class HistoryListener implements Listener, HistoryReader {
    private final Map<Long,Message> history=new HashMap<Long,Message>();
    @Override
    public void onUpdated(Message msg) {
        history.put(msg.getId(),new Message(msg));
    }

    @Override
    public Optional<Message> findMessageById(long id) {
         return Optional.ofNullable(history.get(id));
    }
}
