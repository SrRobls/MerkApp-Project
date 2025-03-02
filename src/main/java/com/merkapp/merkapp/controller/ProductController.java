package com.merkapp.merkapp.controller;

import com.merkapp.merkapp.dtos.response.ProductResponseDTO;
import com.merkapp.merkapp.exception.MerkAppApiException;
import com.merkapp.merkapp.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> obtenerProductos() {
        return ResponseEntity.ok(productService.obtenerProductos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> obtenerProducto(@PathVariable Long id) {
        ResponseEntity<ProductResponseDTO> product = productService.obtenerProducto(id);
        return product;
    }

    @GetMapping(path = "/store/{storeId}")
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
            @RequestParam("image") MultipartFile imageFile) throws MissingServletRequestParameterException {
        try {
            return productService.crearProducto(name, description, category, price, storeId, imageFile);
        } catch (MissingServletRequestParameterException e) {
            // TODO: handle exception
            throw new MerkAppApiException(HttpStatus.BAD_REQUEST, "Es necesario todos los parámetros");
        }
    }

    @PutMapping(path = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ProductResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("price") Double price,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        return productService.actualizarProducto(id, name, description, category, price, imageFile);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        return productService.eliminarProducto(id);
    }

    @GetMapping(path = "/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> obtenerProductosPorCategoria(@PathVariable String category) {
        return ResponseEntity.ok(productService.obtenerProductosPorCategoria(category));
    }

}
