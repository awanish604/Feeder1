package com.ulok.feedback.constants;

public class FeedbackConstants {
	public static final String POST_FEEDBACK = "INSERT INTO FEEDBACK_TABLE VALUES (?,?,?,?)";
	public static final String NEXT_SEQUENCE = "select FeedbackSequence.nextval from dual"; 

}
