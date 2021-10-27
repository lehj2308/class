package model.tBoard;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class TBoardVO {

	private int id;
	private String title;
	private String writer;
	private String content;
	private Date wdate;
	private MultipartFile fileUpload;
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public MultipartFile getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(MultipartFile fileUpload) {
		this.fileUpload = fileUpload;
	}
	@Override
	public String toString() {
		return "TBoardVO [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content + ", wdate="
				+ wdate + "]";
	}

	
	
}
