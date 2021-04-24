package com.example.springsocial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

	/**
	 * Find Favorites by user's id
	 * @param userId
	 * @return
	 */
	List<Favorite> findByUserId(Long userId);

	/**
	 * Find Favorites by user's id and book's id
	 * @param userId
	 * @param bookId
	 * @return
	 */
	Optional<Favorite> findByUserIdAndBookId(Long userId, String bookId);
}
