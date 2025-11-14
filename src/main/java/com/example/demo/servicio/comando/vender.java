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

        for (comando it : items) {
            mueble m = repo.findById(it.getIdMueble()).orElse(null);
            if (m == null) {
                return "no existe el mueble con id " + it.getIdMueble();
            }
            if (m.getStock() < it.getCantidad()) {
                return "stock insuficiente";
            }
        }

        double total = 0.0;

        for (comando it : items) {
            mueble m = repo.findById(it.getIdMueble()).orElseThrow();
            double precioUnit = varS.precioVariante(it.getVariante(), m.getPrecio_base());
            total += precioUnit * it.getCantidad();
            m.setStock(m.getStock() - it.getCantidad());
            repo.save(m);
        }

        return "venta confirmada: total " + total;
    }
}
