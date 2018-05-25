package com.ulok.feedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ulok.feedback.constants.FeedbackConstants;
import com.ulok.feedback.interfaces.IFeedbackDao;
import com.ulok.feedback.utils.DBUtils;
import com.ulok.feedback.vo.FeedbackVO;

public class OracleFeedbackDao implements IFeedbackDao {
	public Connection con=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;

	@Override
	public int postFeedback(FeedbackVO feedback) {
		int result = 0;
		
		//create sequence number
		int feedbackNumber = 0;
		con = DBUtils.getConnection();
		String sequenceQuery=FeedbackConstants.NEXT_SEQUENCE;
		try {
			ps=con.prepareStatement(sequenceQuery);
			rs=ps.executeQuery();
			if(rs.next())
			{
				feedbackNumber = rs.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("feedback sequence:"+feedbackNumber);
		String query = FeedbackConstants.POST_FEEDBACK;
		try {
			//Initialise ps
			ps = null;
			ps = con.prepareStatement(query);
			ps.setInt(1, feedbackNumber);
			ps.setString(2, feedback.getName());
			ps.setString(3, feedback.getComment());
			ps.setInt(4, feedback.getStar());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtils.releaseResource(con, ps, rs);
		}
		return result;
	}

}
