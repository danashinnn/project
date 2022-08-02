package toy.todo.vo;

import java.sql.Date;

public class Todo {

	private int no;
	private String title;
	private String content;
	private String status;
	private Date writeDate;

	public Todo() {
		super();
	}

	public Todo(int no, String title, String content, String status, Date writeDate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.status = status;
		this.writeDate = writeDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
}
