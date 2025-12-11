package com.example.demo.servicio.comando;

import com.example.demo.entidad.comando;
import com.example.demo.entidad.mueble;
import com.example.demo.repositorio.muebleR;
import com.example.demo.servicio.varianteS;

import java.util.List;

public class vender implements opcion {

    private final muebleR repo;
    private final varianteS varS;

    public vender(muebleR repo, varianteS varS) {
        this.repo = repo;
        this.varS = varS;
    }

    @Override
    public String ejecutar(List<comando> items) {
        if (items == null || items.isEmpty()) {
            return "no se enviaron muebles";
        }

        // PRIMER PASO: validar existencia, estado y stock
        for (comando it : items) {
            mueble m = repo.findById(it.getIdMueble()).orElse(null);
            if (m == null) {
                return "no existe el mueble con id " + it.getIdMueble();
            }

            // <<< NUEVO: validar estado activo >>>
            String estado = m.getEstado();
            if (estado == null || !estado.equalsIgnoreCase("activo")) {
                return "el mueble con id " + it.getIdMueble()
                        + " está inactivo, no se puede vender, elige productos con estado activo!";
            }

            if (m.getStock() < it.getCantidad()) {
                return "stock insuficiente en un producto, "
                        + "ingresa todos los productos con stock suficiente!";
            }
        }

        // SEGUNDO PASO: calcular total y descontar stock
        double total = 0.0;

        for (comando it : items) {
            mueble m = repo.findById(it.getIdMueble()).orElseThrow();
            double precioUnit = varS.precioVariante(it.getVariante(), m.getPrecio_base());
            total += precioUnit * it.getCantidad();

            m.setStock(m.getStock() - it.getCantidad());
            repo.save(m);
        }

        return "- VENTA REALIZADA - TOTAL [" + total + "] PESOS";
    }
}
