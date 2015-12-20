package domain;

import org.apache.solr.client.solrj.beans.Field;

public class Demo {
	@Field("id")
	private int id;
	@Field("title")
	private String title;
	@Field("author")
	private String author;
	@Field("text")
	private String text;

	public Demo() {

	}

	public Demo(int id, String title, String author, String text) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
