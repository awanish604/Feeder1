package com.ulok.feedback.service;

import com.ulok.feedback.constants.FeedbackConstants;
import com.ulok.feedback.dao.MockFeedbackDao;
import com.ulok.feedback.dao.MongoFeedbackDao;
import com.ulok.feedback.dao.OracleFeedbackDao;
import com.ulok.feedback.dao.SQLFeedbackDao;
import com.ulok.feedback.interfaces.IFeedbackDao;
import com.ulok.feedback.vo.FeedbackVO;

public class FeedbackService {
	String db = FeedbackConstants.PREFERRED_DB;
	IFeedbackDao dao;
	
	public FeedbackService() {
		super();
		if("Mongo".equalsIgnoreCase(db)) {
			dao = new MongoFeedbackDao();
		}
		else if("Oracle".equalsIgnoreCase(db)) {
			dao = new OracleFeedbackDao();
		}
		else if("SQL".equalsIgnoreCase(db)) {
			dao = new SQLFeedbackDao();
		}
		else {
			dao = new MockFeedbackDao();
		}
	}
	
	public int postFeedback(FeedbackVO feedback) {
		return dao.postFeedback(feedback);
	}

}
