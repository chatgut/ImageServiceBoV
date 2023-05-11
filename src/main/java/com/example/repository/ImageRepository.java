package com.example.repository;

import com.example.entity.ImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.ListCrudRepository;

@Transactional
public interface ImageRepository extends ListCrudRepository<ImageEntity, Long> {
    ImageEntity findByName(String name);
}
