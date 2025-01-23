package com.example.utils;

import com.example.exceptions.ExcepcionPersonalizada;

import java.util.Date;
import java.util.List;

public class Validaciones {

//    validamos que un string no sea nulo o no esté vacía ni contenga números
    public static void validarString(String nombre, String mensaje) throws ExcepcionPersonalizada {
        if (nombre == null || nombre.trim().isEmpty()|| nombre.matches(".*\\d.*")) throw new ExcepcionPersonalizada(mensaje);
    }

//    validamos que un Integer no sea nulo o sea menor de 0
    public static void validarNumero(Integer numero, String mensaje) throws ExcepcionPersonalizada {
        if (numero == null || numero <= 0) throw new ExcepcionPersonalizada(mensaje);

    }

//    Validamos que un Date no sea nulo
    public static void validarDate (Date fecha, String mensaje) throws ExcepcionPersonalizada {
        if (fecha == null) throw new ExcepcionPersonalizada(mensaje);
    }

//    Validamos que una Lista no sea nulo o este vacia
    public static void validarLista(List<?> lista, String mensaje) throws ExcepcionPersonalizada {
        if (lista == null || lista.isEmpty()) throw new ExcepcionPersonalizada(mensaje);
    }
}
