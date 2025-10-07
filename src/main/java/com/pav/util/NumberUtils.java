package com.pav.util;

public class NumberUtils {
    public static int parseIntOrDefault(String arg, int defaultInt) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return defaultInt;
        }
    }
}
