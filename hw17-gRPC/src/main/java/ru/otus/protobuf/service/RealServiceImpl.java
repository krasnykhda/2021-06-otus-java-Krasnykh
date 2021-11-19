package ru.otus.protobuf.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RealServiceImpl implements RealService {

    private final List<Long> sequence;

    public RealServiceImpl() {
        sequence = new ArrayList<>();
    }

    @Override
    public List<Long> getSequence(long firstValue, long lastValue) {
        for (long i = firstValue; i <= lastValue; i++) {
            sequence.add(i);
        }
        return sequence;
    }
}
