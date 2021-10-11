package ru.otus.services;

import ru.otus.crm.model.Client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TemplateProcessor {
    String getPage(String filename, Map<String, Object> data) throws IOException;
}
