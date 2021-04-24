package com.example.springsocial.service;

import java.util.Optional;

import com.example.springsocial.dto.FavoriteDto;
import com.example.springsocial.model.Book;

public interface IBookApiService {

	/**
	 * create new book
	 * 
	 * @param book
	 * @return Book created
	 */
	public Book save(Book user);

	/**
	 * find book by Id
	 * 
	 * @param id
	 * @return Optional Book
	 */
	public Optional<Book> findById(String id);

	/**
	 * mark as favorite
	 * @param favorite
	 * @return
	 */
	public FavoriteDto markFavorite(FavoriteDto favorite);

	/**
	 * remove from favorite
	 * @param favorite
	 * @return
	 */
	public FavoriteDto removeFavorite(FavoriteDto favorite);

}
