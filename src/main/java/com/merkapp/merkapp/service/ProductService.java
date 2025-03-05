package com.merkapp.merkapp.service;

import com.merkapp.merkapp.dtos.response.ProductResponseDTO;
import com.merkapp.merkapp.exception.MerkAppApiException;
import com.merkapp.merkapp.model.Product;
import com.merkapp.merkapp.model.Store;
import com.merkapp.merkapp.repository.ProductRepository;
import com.merkapp.merkapp.repository.StoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final S3Service s3Service;

    public ProductService(ProductRepository productRepository, StoreRepository storeRepository, S3Service s3Service) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.s3Service = s3Service;
    }

    public List<ProductResponseDTO> obtenerProductos() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ProductResponseDTO> obtenerProducto(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.map(product -> ResponseEntity.ok(convertToDTO(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<ProductResponseDTO> obtenerProductosPorTienda(Long storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ResponseEntity<ProductResponseDTO> crearProducto(String name, String description, String category,
                                                            Double price, Long storeId, MultipartFile imageFile) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);

        if (storeOptional.isEmpty()) {
            throw new MerkAppApiException(HttpStatus.NOT_FOUND, "La tienda especificada no existe.");
        }

        Store store = storeOptional.get();

        String imageUrl = s3Service.uploadFile(imageFile);

        Product product = new Product(name, description, category, imageUrl, price, store);

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedProduct));
    }

    public ResponseEntity<ProductResponseDTO> actualizarProducto(Long id, String name, String description,
                                                                 String category, Double price, MultipartFile imageFile) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new MerkAppApiException(HttpStatus.NOT_FOUND, "Producto no encontrado.");
        }

        Product product = productOptional.get();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);

        if (imageFile != null && !imageFile.isEmpty()) {

            String newImageUrl = s3Service.uploadFile(imageFile);
            product.setImage(newImageUrl);
        }

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(convertToDTO(updatedProduct));
    }

    public ResponseEntity<Void> eliminarProducto(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new MerkAppApiException(HttpStatus.NOT_FOUND, "Producto no encontrado.");
        }

        Product product = productOptional.get();

        productRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public List<ProductResponseDTO> obtenerProductosPorCategoria(String category) {
        List<Product> products = productRepository.findByCategoryIgnoreCase(category);
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductResponseDTO convertToDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getImage(),
                product.getPrice(),
                product.getStore().getId());
    }
}