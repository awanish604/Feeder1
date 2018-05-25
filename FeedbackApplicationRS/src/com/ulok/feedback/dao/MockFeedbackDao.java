package com.ulok.feedback.dao;

import com.ulok.feedback.interfaces.IFeedbackDao;
import com.ulok.feedback.vo.FeedbackVO;

public class MockFeedbackDao implements IFeedbackDao {

	@Override
	public int postFeedback(FeedbackVO feedback) {
		System.out.println(">>>>inside Mock Feedback");

		return 0;
	}

}
