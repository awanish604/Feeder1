package com.ulok.feedback.service;

import com.ulok.feedback.dao.FeedbackDao;
import com.ulok.feedback.vo.FeedbackVO;

public class FeedbackService {
	
	FeedbackDao dao = new FeedbackDao();

	public int postFeedback(FeedbackVO feedback) {
		return dao.postFeedback(feedback);
	}

}
