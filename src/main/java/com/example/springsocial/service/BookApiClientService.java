package com.example.springsocial.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springsocial.dto.BookDto;
import com.example.springsocial.model.Favorite;
import com.example.springsocial.repository.FavoriteRepository;
import com.example.springsocial.util.JsonParseUtil;

@Service
public class BookApiClientService implements IBookApiClientService {

	private static final String GET_BOOKS = "https://www.googleapis.com/books/v1/volumes?q=";

	@Autowired
	private RestTemplate restTemplate;

	private JsonParseUtil json;

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Override
	public List<BookDto> getBooksByQuery(final Long userId, final String query) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(GET_BOOKS + query, HttpMethod.GET, entity, String.class);

		json = new JsonParseUtil(result.getBody());

		final List<BookDto> books = getBookDto(json);
		bindFavorite(userId, books);

		return books;
	}

	private void bindFavorite(Long userId, final List<BookDto> books) {

		final List<Favorite> favorites = favoriteRepository.findByUserId(userId);

		favorites.forEach(favorite -> {
			final Optional<BookDto> findBook = books.stream()
					.filter(book -> book.getBookId().equals(favorite.getBookId())).findAny();
			if (findBook.isPresent()) {
				findBook.get().setFavorite(true);
			}
		});

	}

	private List<BookDto> getBookDto(JsonParseUtil json) {
		final List<BookDto> books = new ArrayList<>();
		final int numTests = json.read("$.items.length()", Integer.class);
		for (int i = 0; i < numTests; i++) {
			final String path = "$.items[" + i + "]";
			final BookDto book = new BookDto(path, json);

			books.add(book);
		}
		return books;
	}

}
