package ru.otus.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.handler.ComplexProcessor;
import ru.otus.handler.ComplexProcessorTest;
import ru.otus.model.Message;
import ru.otus.listener.Listener;
import ru.otus.processor.Processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProcessorWithExceptionTest {
    @Test
    @DisplayName("Тест исключения в четную секунду")
    void handleProcessorTest() {
        var timer = mock(Timer.class);
        var processor1 = new ProcessorWithException(timer);
        var message = new Message.Builder(1L).field7("field7").build();
        when(timer.getTime()).thenReturn(100000L);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> processor1.process(message));

    }
}

