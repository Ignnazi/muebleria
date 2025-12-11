package com.example.demo.controlador;
import com.example.demo.entidad.comando;
import com.example.demo.servicio.cotizarVentaS;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/op")
public class cotizarVentaC {

    private final cotizarVentaS cv;

    public cotizarVentaC(cotizarVentaS cv) {
        this.cv = cv;
    }

    @PostMapping("/cotizar")
    public String cotizarMuebles(@RequestBody List<comando> items) {
        return cv.cotizarMuebles(items);
    }

    @PostMapping("/vender")
    public String venderMuebles(@RequestBody List<comando> items) {
        return cv.venderMuebles(items);
    }
}

