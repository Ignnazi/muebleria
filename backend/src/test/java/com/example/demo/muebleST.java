package com.example.demo;

import com.example.demo.entidad.mueble;
import com.example.demo.repositorio.muebleR;
import com.example.demo.servicio.muebleS;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class muebleST {

    @Test
    void guardarMueble() {
        muebleR repo=mock(muebleR.class);
        muebleS serv=new muebleS(repo);

        mueble m=new mueble();
        m.setNombre_mueble("Silla gamer");
        m.setPrecio_base(40000);
        when(repo.save(m)).thenReturn(m);

        mueble guardado=serv.guardarMueble(m);
        assertEquals("Silla gamer", guardado.getNombre_mueble());
    }
}
