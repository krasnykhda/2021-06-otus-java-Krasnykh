package ru.otus.processor;

import ru.otus.model.Message;

import java.util.Date;

public class ProcessorUpperField10 implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field4(message.getField10().toUpperCase()).build();
    }
}