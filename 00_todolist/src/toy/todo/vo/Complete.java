package toy.todo.vo;

import java.sql.Date;

public class Complete {

	private int comNo;
	private int todoNo;
	private String comTitle;
	private String comContent;
	private Date startDate;
	private Date endDate;

	public Complete() {
		super();
	}

	public Complete(int comNo, int todoNo, String comTitle, String comContent, Date startDate, Date endDate) {
		super();
		this.comNo = comNo;
		this.todoNo = todoNo;
		this.comTitle = comTitle;
		this.comContent = comContent;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public int getTodoNo() {
		return todoNo;
	}

	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}

	public String getComTitle() {
		return comTitle;
	}

	public void setComTitle(String comTitle) {
		this.comTitle = comTitle;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
