package com.example.demo.controlador;

import com.example.demo.entidad.mueble;
import com.example.demo.servicio.muebleS;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mueble")
public class muebleC {

    private final muebleS s;

    public muebleC(muebleS s) {
        this.s=s;
    }

    @GetMapping
    public List<mueble> listarPostman() {
        return s.listarMuebles();
    }

    @GetMapping("/{id}")
    public mueble obtenerIDPostman(@PathVariable Integer id) {
        return s.IDMueble(id).orElse(null);
    }

    @PostMapping
    public mueble crearPostman(@RequestBody mueble m) {
        return s.guardarMueble(m);
    }

    @PutMapping("/{id}")
    public mueble modificarPostman(@PathVariable Integer id, @RequestBody mueble m) {
        m.setID_mueble(id);
        return s.guardarMueble(m);
    }

      @PatchMapping("/{id}/cambiarEstado")
    public mueble desactivarPostman(@PathVariable Integer id) {
        return s.desactivarMueble(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarPostman(@PathVariable Integer id) {
        s.eliminarMueble(id);
    }
  
}
