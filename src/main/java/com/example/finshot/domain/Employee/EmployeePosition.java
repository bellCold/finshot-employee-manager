package com.example.finshot.domain.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public enum EmployeePosition {
    CEO,
    CTO,
    MANAGER,
    STAFF;

    public static List<String> getPosition(String searchWord) {
        String upperSearchWord = searchWord.toUpperCase(Locale.ROOT);
        return Arrays.stream(EmployeePosition.values()).map(Enum::toString).filter(position -> position.contains(upperSearchWord)).collect(Collectors.toList());
    }
}
