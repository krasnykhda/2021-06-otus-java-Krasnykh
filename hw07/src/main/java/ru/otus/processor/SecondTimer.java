package ru.otus.processor;

import java.util.Date;

public class SecondTimer implements Timer{
    private final Date date=new Date();
    @Override
    public long getTime() {
        return date.getTime()/1000;
    }
}
