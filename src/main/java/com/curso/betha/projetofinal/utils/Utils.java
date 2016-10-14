package com.curso.betha.projetofinal.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NatanRamos on 05/10/2016.
 */
public final class Utils {

    public static final Long parseLong(String value) {
        return isEmpty(value) ? null : Long.parseLong(value);
    }

    public static final boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static final boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static final Map<String, String> parseMap(HttpServletRequest req) {
        Map<String, String> dados = new HashMap<>();
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            dados.put(key, req.getParameter(key));
        }
        return dados;
    }

    public static Date parseDate(String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return isEmpty(value) ? null : sdf.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int getMes(int mes){
        switch (mes) {
            case 1: return Calendar.JANUARY;
            case 2: return Calendar.FEBRUARY;
            case 3: return Calendar.MARCH;
            case 4: return Calendar.APRIL;
            case 5: return Calendar.MAY;
            case 6: return Calendar.JUNE;
            case 7: return Calendar.JULY;
            case 8: return Calendar.AUGUST;
            case 9: return Calendar.SEPTEMBER;
            case 10: return Calendar.OCTOBER;
            case 11: return Calendar.NOVEMBER;
            case 12: return Calendar.DECEMBER;
        }
        return 0;
    }

    public static Date getData(String data){
        int dia = Integer.valueOf(data.substring(8));
        int mes = Integer.valueOf(data.substring(5, 7));
        int ano = Integer.valueOf(data.substring(0, 4));
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, getMes(mes), dia);
        return calendar.getTime();
    }
}
