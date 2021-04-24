package com.example.springsocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * find Book by Id
	 * @param id
	 * @return
	 */
	Optional<Book> findByBookId(String id);

}
