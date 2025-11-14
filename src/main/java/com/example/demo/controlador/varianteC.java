package com.example.demo.controlador;

import com.example.demo.entidad.variante;
import com.example.demo.servicio.varianteS;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/variante")
public class varianteC {

    private final varianteS s;

    public varianteC(varianteS s) {
        this.s = s;
    }

    @GetMapping
    public List<variante> listar() {
        return s.listar();
    }

    @GetMapping("/{nombre}")
    public variante obtener(@PathVariable String nombre) {
        return s.obtener(nombre);
    }

    @PostMapping
    public variante crear(@RequestBody variante v) {
        return s.guardar(v);
    }

    @PutMapping("/{nombre}")
    public variante editar(@PathVariable String nombre, @RequestBody variante v) {
        v.setNombre_variante(nombre);
        return s.guardar(v);
    }

    @DeleteMapping("/{nombre}")
    public void borrar(@PathVariable String nombre) {
        s.eliminar(nombre);
    }
}
