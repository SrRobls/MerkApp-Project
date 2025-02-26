package com.merkapp.merkapp.controller;

import com.merkapp.merkapp.dtos.request.StoreDTO;
import com.merkapp.merkapp.dtos.response.StoreResponseDTO;
import com.merkapp.merkapp.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreResponseDTO>> obtenerTiendas() {
        return ResponseEntity.ok(storeService.obtenerTiendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> obtenerTienda(@PathVariable Long id) {
        return storeService.obtenerTienda(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<StoreResponseDTO> crearTienda(
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("ownerId") Long ownerId,
            @RequestParam("image") MultipartFile imageFile) {
        return storeService.crearTienda(new StoreDTO(name, location, description, ownerId), imageFile);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<StoreResponseDTO> actualizarTienda(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("ownerId") Long ownerId,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        return storeService.actualizarTienda(id, new StoreDTO(name, location, description, ownerId), imageFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTienda(@PathVariable Long id) {
        return storeService.eliminarTienda(id);
    }
}
