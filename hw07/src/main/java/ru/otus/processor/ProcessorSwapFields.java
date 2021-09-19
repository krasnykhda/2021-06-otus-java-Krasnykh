package ru.otus.processor;

import ru.otus.model.Message;

import java.util.Date;

public class ProcessorSwapFields implements Processor{
    @Override
    public Message process(Message message) {
        return message.toBuilder().field1(message.getField2()).field2(message.getField1()).build();
    }
}
