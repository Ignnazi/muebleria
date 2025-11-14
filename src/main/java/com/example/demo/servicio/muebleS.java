package com.example.demo.servicio;

import com.example.demo.entidad.mueble;
import com.example.demo.repositorio.muebleR;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class muebleS {

    private final muebleR r;

    public muebleS(muebleR r) {
        this.r=r;
    }

    public List<mueble> listarMuebles() {
        return r.findAll();
    }

    public Optional<mueble> IDMueble(Integer id) {
        return r.findById(id);
    }

    public mueble guardarMueble(mueble m) {
        return r.save(m);
    }

    public void eliminarMueble(Integer id) {
        r.deleteById(id);
    }

    public mueble desactivarMueble(Integer id) {
        mueble m = r.findById(id).orElseThrow(() -> new RuntimeException("PRUEBA CON UN ID EXISTENTE"));
        m.setEstado("inactivo");//CAMBIA AUTOMATICAMENTE EL ESTADO
        return r.save(m);      
    }
}
