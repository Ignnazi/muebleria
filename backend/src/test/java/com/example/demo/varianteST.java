package com.example.demo;

import com.example.demo.entidad.variante;
import com.example.demo.repositorio.varianteR;
import com.example.demo.servicio.varianteS;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class varianteST {

    @Test
    void precioVariante_sinNombre_devuelveBase() {
        varianteR repo = mock(varianteR.class);
        varianteS s = new varianteS(repo);

        double base = 10000.0;

        double r1 = s.precioVariante(null, base);
        double r2 = s.precioVariante("   ", base);

        assertEquals(base, r1);
        assertEquals(base, r2);
        verifyNoInteractions(repo);
    }

    @Test
    void precioVariante_conPorcentaje_aplicaAumento() {
        varianteR repo = mock(varianteR.class);

        variante v = new variante();
        v.setNombre_variante("barniz premium");
        v.setPorcentaje(10.0);

        when(repo.findById("barniz premium")).thenReturn(Optional.of(v));

        varianteS s = new varianteS(repo);

        double base = 10000.0;
        double r = s.precioVariante("barniz premium", base);

        assertEquals(11000.0, r);
        verify(repo, times(1)).findById("barniz premium");
    }
}
