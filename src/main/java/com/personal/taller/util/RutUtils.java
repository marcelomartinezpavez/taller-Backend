package com.personal.taller.util;

public class RutUtils {
    
    public static String normalizarRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            return null;
        }
        return rut.replace(".", "").replace("-", "").toUpperCase();
    }

    public static String formatearRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            return null;
        }
        String normalized = normalizarRut(rut);
        if (normalized == null || normalized.length() < 2) {
            return rut;
        }
        String numero = normalized.substring(0, normalized.length() - 1);
        String verificador = normalized.substring(normalized.length() - 1);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = numero.length() - 1; i >= 0; i--) {
            if (count > 0 && count % 3 == 0) {
                sb.append(".");
            }
            sb.append(numero.charAt(i));
            count++;
        }
        sb.reverse();
        sb.append("-").append(verificador);
        return sb.toString();
    }
}