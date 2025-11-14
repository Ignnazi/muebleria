package com.example.demo.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "variante")
public class variante {

    @Id
    private String nombre_variante;
    private Double porcentaje;

    public String getNombre_variante() {
        return nombre_variante;
    }

    public void setNombre_variante(String nombre_variante) {
        this.nombre_variante = nombre_variante;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
