package com.example.demo.servicio;

import com.example.demo.entidad.comando;
import com.example.demo.repositorio.muebleR;
import com.example.demo.servicio.comando.cotizar;
import com.example.demo.servicio.comando.opcion;
import com.example.demo.servicio.comando.vender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cotizarVentaS {

    private final opcion cotizar;
    private final opcion vender;

    public cotizarVentaS(muebleR repo, varianteS varS) {
        this.cotizar = new cotizar(repo, varS);
        this.vender = new vender(repo, varS);
    }

    //SIRVE PARA 1 O VARIOS MUEBLES EN AMBOS CASOS
    public String cotizarMuebles(List<comando> items) {
        return cotizar.ejecutar(items);
    }

    public String venderMuebles(List<comando> items) {
        return vender.ejecutar(items);
    }
}
