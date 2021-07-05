package ru.otus;

import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.*;

public class HelloOtus {
    public static void main(String[] args) {
        Multimap<String, String> companyStructure = HashMultimap.create();
        companyStructure.put("IT Department", "Androids developers division");
        companyStructure.put("IT Department", "ML developers division");
        companyStructure.put("IT Department", "JAVA developers division");
        companyStructure.put("Financial management", "Accounting");
        companyStructure.put("Financial management", "Preparation of ifrs financial statements");
        Iterator<String> it = companyStructure.get("IT Department").iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
