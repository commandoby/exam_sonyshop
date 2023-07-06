package com.commandoby.sonyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commandoby.sonyShop.components.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
