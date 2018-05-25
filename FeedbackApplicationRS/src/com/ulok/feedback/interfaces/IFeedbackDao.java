package com.ulok.feedback.interfaces;

import com.ulok.feedback.vo.FeedbackVO;

public  interface IFeedbackDao {
	
	public int postFeedback(FeedbackVO feedback);

}
