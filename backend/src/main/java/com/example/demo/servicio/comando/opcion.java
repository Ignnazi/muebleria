package com.example.demo.servicio.comando;

import com.example.demo.entidad.comando;

import java.util.List;

public interface opcion {
    String ejecutar(List<comando> items);
}
