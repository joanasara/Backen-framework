package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Modifying
	@Query(value = "delete from photo_file where publication_id = :id", nativeQuery = true)
	public void deletePhotoFilesByPublicationId(@Param("id") Long id);
}
