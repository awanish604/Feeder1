package com.ulok.feedback.dao;

import com.ulok.feedback.interfaces.IFeedbackDao;
import com.ulok.feedback.vo.FeedbackVO;

public class SQLFeedbackDao implements IFeedbackDao {

	@Override
	public int postFeedback(FeedbackVO feedback) {
		System.out.println(">>>>inside SQL Feedback");
		return 0;
	}

}
