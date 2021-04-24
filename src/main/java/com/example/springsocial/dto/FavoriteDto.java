package com.example.springsocial.dto;

import java.io.Serializable;

import com.example.springsocial.model.Favorite;

public class FavoriteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String bookId;

	public FavoriteDto() {

	}

	public FavoriteDto(Favorite favorite) {
		this.userId = favorite.getUserId();
		this.bookId = favorite.getBookId();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

}
