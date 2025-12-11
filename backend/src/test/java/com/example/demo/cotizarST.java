package com.example.demo;

import com.example.demo.entidad.comando;
import com.example.demo.entidad.mueble;
import com.example.demo.repositorio.muebleR;
import com.example.demo.servicio.varianteS;
import com.example.demo.servicio.comando.cotizar;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class cotizarST {

    @Test
    void ejecutar_ok_calculaTotal() {
        muebleR repo = mock(muebleR.class);
        varianteS varS = mock(varianteS.class);

        mueble m = new mueble();
        m.setID_mueble(1);
        m.setNombre_mueble("Silla");
        m.setPrecio_base(10000.0);
        m.setStock(10);

        when(repo.findById(1)).thenReturn(Optional.of(m));
        when(varS.precioVariante("normal", 10000.0)).thenReturn(10000.0);

        comando c = new comando();
        c.setIdMueble(1);
        c.setVariante("normal");
        c.setCantidad(2);

        cotizar op = new cotizar(repo, varS);

        String resp = op.ejecutar(List.of(c));

        assertEquals("cotización generada: total 20000.0", resp);
    }

    @Test
    void ejecutar_sinStock() {
        muebleR repo = mock(muebleR.class);
        varianteS varS = mock(varianteS.class);

        mueble m = new mueble();
        m.setID_mueble(1);
        m.setNombre_mueble("Silla");
        m.setPrecio_base(10000.0);
        m.setStock(0);

        when(repo.findById(1)).thenReturn(Optional.of(m));

        comando c = new comando();
        c.setIdMueble(1);
        c.setVariante("normal");
        c.setCantidad(1);

        cotizar op = new cotizar(repo, varS);

        String resp = op.ejecutar(List.of(c));

        assertEquals("stock insuficiente", resp);
    }
}
