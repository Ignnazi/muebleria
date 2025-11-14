package com.example.demo.servicio;

import com.example.demo.entidad.variante;
import com.example.demo.repositorio.varianteR;
import com.example.demo.servicio.estrategia.porcentaje;
import com.example.demo.servicio.estrategia.precio;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class varianteS {

    private final varianteR r;

    public varianteS(varianteR r) {
        this.r=r;
    }
    //calcula el precio de cualquier variante segun su porcentaje
    public double precioVariante(String nombre_variante, double porcentaje) {
        if (nombre_variante==null||nombre_variante.isBlank()) {
            return porcentaje;
        }
        variante v = r.findById(nombre_variante).orElse(null);

        if (v==null || v.getPorcentaje()==null) {
            return porcentaje;
        }
        precio nuevo=new porcentaje(v.getPorcentaje());

        return nuevo.actualizarPrecio(porcentaje);
    }

    public List<variante> listar() {
        return r.findAll();
    }

    public variante obtener(String nombre) {
        return r.findById(nombre).orElse(null);
    }

    public variante guardar(variante v) {
        return r.save(v);
    }

    public void eliminar(String nombre) {
        r.deleteById(nombre);
    }

}
