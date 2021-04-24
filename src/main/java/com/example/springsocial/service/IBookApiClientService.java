package com.example.springsocial.service;

import java.util.List;

import com.example.springsocial.dto.BookDto;

public interface IBookApiClientService {

	/**
	 * get books by query
	 * @param UserId
	 * @param query
	 * @return
	 */
	List<BookDto> getBooksByQuery(Long UserId, String query);

}
