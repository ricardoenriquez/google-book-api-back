package com.example.springsocial.dto;

import java.io.Serializable;

import com.example.springsocial.util.JsonParseUtil;

public class BookDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bookId;

	private String title;

	private String thumbnail;

	private Boolean favorite = false;

	public BookDto(String path, JsonParseUtil json) {

		// Retrieves the test
		this.bookId = json.read(path, "id", String.class);
		this.title = json.read(path, "volumeInfo.title", String.class);
		this.thumbnail = json.read(path, "volumeInfo.imageLinks.thumbnail", String.class);
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

}
