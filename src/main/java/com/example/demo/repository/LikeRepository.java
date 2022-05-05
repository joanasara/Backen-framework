package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Like;



public interface LikeRepository extends JpaRepository<Like, Long> {

	@Modifying
	@Query(value = "delete from publication_like where publication_id = :id", nativeQuery = true)
	public void deleteLikesFromPublicationId(@Param("id") Long id);

	@Modifying
	@Query(value = "delete from publication_like where publication_id = :publicationId and user_id = :userId", nativeQuery = true)
	public void deleteLikeFromPublicationAndUser(@Param("publicationId") Long publicationId,
			@Param("userId") Long userId);
}
