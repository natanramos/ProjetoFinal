package com.curso.betha.projetofinal.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NatanRamos on 05/10/2016.
 */
public final class Utils {

    public static final Long parseLong(String value) {
        return isEmpty(value) ? null : Long.parseLong(value);
    }

    public static final Double parseDouble(String value) {
        return isEmpty(value) ? null : Double.parseDouble(value);
    }

    public static final Integer parseInteger(String value) {
        return isEmpty(value) ? null : Integer.parseInt(value);
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return isEmpty(value) ? null : sdf.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date parseDateTime(String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return isEmpty(value) ? null : sdf.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String limparString(String texto) {
        texto = texto.replace(".","").trim();
        texto = texto.replace("/","").trim();
        texto = texto.replace("-","").trim();
        texto = texto.replace("(","").trim();
        texto = texto.replace(")","").trim();
        texto = texto.replace(" ","").trim();
        return texto;
    }
}
