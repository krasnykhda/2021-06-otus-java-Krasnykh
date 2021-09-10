package ru.otus.processor;

import ru.otus.model.Message;

import java.util.Date;
import java.util.TimerTask;

public class ProcessorWithException implements Processor {
    private final Timer timer;

    public ProcessorWithException(Timer timer) {
        this.timer = timer;
    }

    @Override
    public Message process(Message message) {
        long time=timer.getTime();
        if(time%2==0){
            throw new RuntimeException("Ошибка в четную секунду");
        }
        return message.toBuilder().field1(message.getField2()).field2(message.getField1()).build();
    }
}
