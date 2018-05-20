package com.ulok.feedback.vo;

public class FeedbackVO {
	private String name;
	private String comment;
	private int star;
	
	
	/**
	 * @param name
	 * @param comment
	 * @param star
	 */
	public FeedbackVO(String name, String comment, int star) {
		super();
		this.name = name;
		this.comment = comment;
		this.star = star;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}

}
