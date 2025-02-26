package com.merkapp.merkapp.controller;

import com.merkapp.merkapp.dtos.response.ProductResponseDTO;
import com.merkapp.merkapp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> obtenerProductos() {
        return ResponseEntity.ok(productService.obtenerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> obtenerProducto(@PathVariable Long id) {
        return productService.obtenerProducto(id);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductResponseDTO>> obtenerProductosPorTienda(@PathVariable Long storeId) {
        return ResponseEntity.ok(productService.obtenerProductosPorTienda(storeId));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProductResponseDTO> crearProducto(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("price") Double price,
            @RequestParam("storeId") Long storeId,
            @RequestParam("image") MultipartFile imageFile) {
        return productService.crearProducto(name, description, category, price, storeId, imageFile);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ProductResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("price") Double price,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        return productService.actualizarProducto(id, name, description, category, price, imageFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        return productService.eliminarProducto(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> obtenerProductosPorCategoria(@PathVariable String category) {
        return ResponseEntity.ok(productService.obtenerProductosPorCategoria(category));
    }


}
