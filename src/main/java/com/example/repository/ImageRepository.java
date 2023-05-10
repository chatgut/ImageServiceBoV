package com.example.repository;

import com.example.entity.ImageEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ImageRepository extends ListCrudRepository<ImageEntity, Long> {
}
