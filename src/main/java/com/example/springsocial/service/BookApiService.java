package com.example.springsocial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.dto.FavoriteDto;
import com.example.springsocial.model.Book;
import com.example.springsocial.model.Favorite;
import com.example.springsocial.repository.BookRepository;
import com.example.springsocial.repository.FavoriteRepository;

@Service
public class BookApiService implements IBookApiService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Override
	public Book save(final Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> findById(final String id) {
		return bookRepository.findByBookId(id);
	}

	@Override
	public FavoriteDto markFavorite(final FavoriteDto favoriteDto) {

		final Optional<Favorite> favorite = favoriteRepository.findByUserIdAndBookId(favoriteDto.getUserId(),
				favoriteDto.getBookId());
		if (favorite.isPresent()) {
			final Favorite favoriteToEdit = favorite.get();
			favoriteToEdit.setFavorite(true);
			return new FavoriteDto(favoriteRepository.save(favoriteToEdit));
		} else {
			final Book newBook = saveBook(favoriteDto);
			return new FavoriteDto(
					favoriteRepository.save(new Favorite(favoriteDto.getUserId(), newBook.getBookId(), true)));
		}
	}

	@Override
	public FavoriteDto removeFavorite(final FavoriteDto favoriteDto) {
		final Optional<Favorite> favorite = favoriteRepository.findByUserIdAndBookId(favoriteDto.getUserId(),
				favoriteDto.getBookId());
		if (favorite.isPresent()) {
			final Favorite favoriteToEdit = favorite.get();
			favoriteToEdit.setFavorite(false);
			return new FavoriteDto(favoriteRepository.save(favoriteToEdit));
		}
		return favoriteDto;
	}

	private Book saveBook(FavoriteDto favoriteDto) {
		return bookRepository.save(new Book(favoriteDto.getBookId()));
	}

}
