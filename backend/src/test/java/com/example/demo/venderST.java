package com.example.demo;

import com.example.demo.entidad.comando;
import com.example.demo.entidad.mueble;
import com.example.demo.repositorio.muebleR;
import com.example.demo.servicio.varianteS;
import com.example.demo.servicio.comando.vender;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class venderST {

    @Test
    void ejecutar_ok_descuentaStockYCalculaTotal() {
        muebleR repo = mock(muebleR.class);
        varianteS varS = mock(varianteS.class);

        mueble m = new mueble();
        m.setID_mueble(1);
        m.setNombre_mueble("Silla");
        m.setPrecio_base(10000.0);
        m.setStock(5);

        when(repo.findById(1)).thenReturn(Optional.of(m));
        when(varS.precioVariante("normal", 10000.0)).thenReturn(10000.0);

        comando c = new comando();
        c.setIdMueble(1);
        c.setVariante("normal");
        c.setCantidad(2);

        vender op = new vender(repo, varS);

        String resp = op.ejecutar(List.of(c));

        assertEquals("venta confirmada: total 20000.0", resp);
        assertEquals(3, m.getStock());
        verify(repo, times(1)).save(m);
    }

    @Test
    void ejecutar_muebleNoExiste() {
        muebleR repo = mock(muebleR.class);
        varianteS varS = mock(varianteS.class);

        when(repo.findById(1)).thenReturn(Optional.empty());

        comando c = new comando();
        c.setIdMueble(1);
        c.setVariante("normal");
        c.setCantidad(1);

        vender op = new vender(repo, varS);

        String resp = op.ejecutar(List.of(c));

        assertEquals("no existe el mueble con id 1", resp);
    }
}

