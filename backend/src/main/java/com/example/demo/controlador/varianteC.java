package com.example.demo.controlador;

import com.example.demo.entidad.variante;
import com.example.demo.servicio.varianteS;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping("/{nombreActual}")
public variante editar(
        @PathVariable String nombreActual,
        @RequestBody variante v
) {

    Double nuevoPorcentaje = v.getPorcentaje();
    String nuevoNombre = v.getNombre_variante();

    variante existente = s.obtener(nombreActual);
    if (existente == null) {
        throw new RuntimeException("La variante no existe: " + nombreActual);
    }

    // Si NO cambia el nombre → solo actualiza porcentaje
    if (nuevoNombre == null || nuevoNombre.isBlank() || nuevoNombre.equals(nombreActual)) {
        existente.setPorcentaje(nuevoPorcentaje);
        return s.guardar(existente);
    }

    // Si cambia el nombre → borrar y crear nueva
    s.eliminar(nombreActual);

    variante nueva = new variante();
    nueva.setNombre_variante(nuevoNombre);
    nueva.setPorcentaje(nuevoPorcentaje);
    return s.guardar(nueva);
}


    @DeleteMapping("/{nombre}")
    public void borrar(@PathVariable String nombre) {
        s.eliminar(nombre);
    }
}
