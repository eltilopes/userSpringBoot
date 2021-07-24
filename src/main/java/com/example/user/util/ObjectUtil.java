package com.example.user.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;

public class ObjectUtil {

    public static <T> Boolean isNullOrEmty(T valor) {
        if (valor == null) {
            return true;
        }

        if (valor instanceof List) {
            return ((List<?>) valor).isEmpty();
        }

        if(valor instanceof String) {
            return ((String) valor).trim().isEmpty();
        }

        if(valor instanceof Integer) {
            return ((Integer) valor == 0);
        }

        if(valor instanceof Long) {
            return ((Long) valor == 0);
        }

        return false;
    }

    public static String getJsonFromObject(Object objeto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(objeto);
    }

}
