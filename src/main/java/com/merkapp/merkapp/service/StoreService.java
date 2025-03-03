package com.merkapp.merkapp.service;

import com.merkapp.merkapp.dtos.request.StoreDTO;
import com.merkapp.merkapp.dtos.response.StoreResponseDTO;
import com.merkapp.merkapp.enums.Role;
import com.merkapp.merkapp.exception.MerkAppApiException;
import com.merkapp.merkapp.model.Store;
import com.merkapp.merkapp.model.User;
import com.merkapp.merkapp.repository.StoreRepository;
import com.merkapp.merkapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    public StoreService(StoreRepository storeRepository, UserRepository userRepository, S3Service s3Service) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }


    public List<StoreResponseDTO> obtenerTiendas() {
        return storeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<StoreResponseDTO> obtenerTienda(Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        return storeOptional.map(store -> ResponseEntity.ok(convertToDTO(store)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public ResponseEntity<StoreResponseDTO> crearTienda(StoreDTO storeDTO, MultipartFile imageFile) {
        Optional<User> ownerOptional = userRepository.findById(storeDTO.getOwnerId());

        if (ownerOptional.isEmpty() || !ownerOptional.get().getAuthorities().contains(Role.ROLE_ADMIN)) {
            throw new MerkAppApiException(HttpStatus.FORBIDDEN, "Solo los ADMIN pueden crear tiendas.");
        }

        if (storeRepository.existsByName(storeDTO.getName())) {
            throw new MerkAppApiException(HttpStatus.CONFLICT, "Ya existe una tienda con ese nombre.");
        }


        String imageUrl = s3Service.uploadFile(imageFile);

        Store store = new Store(storeDTO.getName(), storeDTO.getLocation(), storeDTO.getDescription(), imageUrl, ownerOptional.get());
        Store savedStore = storeRepository.save(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedStore));
    }

    public ResponseEntity<StoreResponseDTO> actualizarTienda(Long id, StoreDTO storeDTO, MultipartFile imageFile) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isEmpty()) {
            throw new MerkAppApiException(HttpStatus.NOT_FOUND, "Tienda no encontrada.");
        }

        Store store = storeOptional.get();
        store.setName(storeDTO.getName());
        store.setLocation(storeDTO.getLocation());
        store.setDescription(storeDTO.getDescription());


        if (imageFile != null && !imageFile.isEmpty()) {

            String newImageUrl = s3Service.uploadFile(imageFile);
            store.setImage(newImageUrl);
        }

        Store updatedStore = storeRepository.save(store);
        return ResponseEntity.ok(convertToDTO(updatedStore));
    }

    public ResponseEntity<Void> eliminarTienda(Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isEmpty()) {
            throw new MerkAppApiException(HttpStatus.NOT_FOUND, "Tienda no encontrada.");
        }

        storeRepository.deleteById(id); // ✅ SOLO ELIMINA DE LA BASE DE DATOS

        return ResponseEntity.noContent().build();
    }



    private StoreResponseDTO convertToDTO(Store store) {
        return new StoreResponseDTO(
                store.getId(),
                store.getName(),
                store.getLocation(),
                store.getDescription(),
                store.getImage(),
                store.getOwner().getUsername(),
                store.getProducts() != null ?
                        store.getProducts().stream()
                                .map(product -> product.getName())
                                .collect(Collectors.toList()) :
                        null
        );
    }
}
