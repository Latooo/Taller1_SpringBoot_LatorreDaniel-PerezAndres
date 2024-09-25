package com.example.Inventario.controller;

import com.example.Inventario.model.Inventario;
import com.example.Inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Permitir solicitudes desde el frontend
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerTodos() {
        try {
            List<Inventario> inventarios = inventarioService.obtenerTodos();
            return ResponseEntity.ok(inventarios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.obtenerPorId(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario inventario) {
        try {
            Inventario nuevoInventario = inventarioService.guardar(inventario);
            return ResponseEntity.status(201).body(nuevoInventario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @RequestBody Inventario detallesInventario) {
        Optional<Inventario> inventarioOptional = inventarioService.obtenerPorId(id);
        if (inventarioOptional.isPresent()) {
            Inventario inventario = inventarioOptional.get();
            inventario.setNombre(detallesInventario.getNombre());
            inventario.setTipo(detallesInventario.getTipo());
            inventario.setCantidad(detallesInventario.getCantidad());
            inventario.setPrecio(detallesInventario.getPrecio());
            return ResponseEntity.ok(inventarioService.guardar(inventario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            inventarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}