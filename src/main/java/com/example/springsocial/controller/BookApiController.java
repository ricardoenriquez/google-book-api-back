package com.example.springsocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.dto.BookDto;
import com.example.springsocial.dto.FavoriteDto;
import com.example.springsocial.service.IBookApiClientService;
import com.example.springsocial.service.IBookApiService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/book")
public class BookApiController {

	@Autowired
	private IBookApiService bookService;

	@Autowired
	private IBookApiClientService bookClientService;

	@GetMapping("/text")
	public String getText() {
		return "prueba";
	}

	@GetMapping("/findBooks/{userId}/{query}")
	public ResponseEntity<List<BookDto>> updateProcessed(@PathVariable("userId") Long userId,
			@PathVariable("query") String query) {
		return new ResponseEntity<>(bookClientService.getBooksByQuery(userId, query), HttpStatus.OK);
	}

	@PutMapping("/markFavorite")
	public ResponseEntity<FavoriteDto> markFavorite(@RequestBody FavoriteDto favorite) {
		return new ResponseEntity<>(bookService.markFavorite(favorite), HttpStatus.OK);
	}

	@PutMapping("/removeFavorite")
	public ResponseEntity<FavoriteDto> deleteFavorite(@RequestBody FavoriteDto favorite) {
		return new ResponseEntity<>(bookService.removeFavorite(favorite), HttpStatus.OK);
	}
}
