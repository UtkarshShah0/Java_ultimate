package com.jpa.one2many.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Page implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	int number;
	
	public Page() {}
	
	public Page(int number, String chapter, String content) {
		super();
		this.number = number;
		this.chapter = chapter;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", number=" + number + ", chapter=" + chapter + ", content=" + content + ", book="
				+ book + "]";
	}

	public Page(int number, String chapter, String content, Book book) {
		super();
		this.number = number;
		this.chapter = chapter;
		this.content = content;
		this.book = book;
	}

	String chapter;
	String content;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public long getId() {
		return id;
	}

	//@Owning side
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="book_id", nullable = false)
	private Book book;
}
