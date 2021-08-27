package com.cg.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bookstore.entities.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}