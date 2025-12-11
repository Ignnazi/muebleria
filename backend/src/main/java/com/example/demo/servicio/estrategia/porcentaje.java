package com.example.demo.servicio.estrategia;

public class porcentaje implements precio {

    private final double p;

    public porcentaje(double p) {
        this.p = p;
    }

    @Override
    public double actualizarPrecio(double precio_base) {
        double total=precio_base+(precio_base*p/100.0);
        return total;
    }
}
