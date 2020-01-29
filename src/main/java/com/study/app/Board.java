package com.study.app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Board {
	private int id;
	private String title;
	private String content;
	private String writer;
	private int hits;
	private Date create_date;
	private Date update_date;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getCreate_date() {
		return simpleDateFormat.format(create_date);
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_date() {
		return simpleDateFormat.format(update_date);
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id).append("/");
		sb.append(this.title).append("/");
		sb.append(this.content).append("/");
		sb.append(this.writer).append("/");
		sb.append(this.hits).append("/");
		sb.append(this.getCreate_date()).append("/");
		sb.append(this.getUpdate_date());
		return sb.toString();
	}
	
}
