package com.ulok.feedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoServerException;
import com.mongodb.WriteResult;
import com.ulok.feedback.constants.DBConstants;
import com.ulok.feedback.constants.FeedbackConstants;
import com.ulok.feedback.utils.DBUtils;
import com.ulok.feedback.vo.FeedbackVO;

public class FeedbackDao {
	public Connection con=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;

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
	
	public int postFeedbackMongoDB(FeedbackVO feedback) {
		int result = 0;
		
		//get MongoDB client named localhost running in port 27017
		MongoClient client = new MongoClient(DBConstants.LOCAL_HOST, DBConstants.MONGODB_PORT_NUMBER);
		/*get DB with name local. If local is not present then
		 * it will be automatically created.
		 */
		DB db = client.getDB(DBConstants.MONGODB_LOCAL);
		/*
		 * get DBCollection with name Feedback. If Feedback is not present
		 * then it will be automatically created.
		 */
		DBCollection col = db.getCollection(DBConstants.FEEDBACK_COLLECTION);
		
		//get count number/index number of DB
		int count = col.getStats().getInt(DBConstants.RECORD_COUNT);
		
		/*createDBObject with index+=1. This object will be in 
		 * JSON format.
		 */
		DBObject feedbackDoc = createDBObject(count+1,feedback);
		
		//insert created feedback in MongoDB.
		WriteResult writeResult = null;
		try {
			writeResult = col.insert(feedbackDoc);
			result = 1;
			
			System.out.println("write result:"+writeResult);
			/*//read example
			DBObject query = BasicDBObjectBuilder.start().add("Name", (feedback.getName())).get();
			DBCursor cursor = col.find(query);
			while(cursor.hasNext()){
				//System.out.println("Fetched result:"+cursor.next());
			}*/
		}catch(MongoServerException e) {
			result = 0;
			System.out.println("MongoServerException:"+e);
			e.printStackTrace();
		}finally {
			//close resources
			client.close();
		}
		
		return result;
	}
	
	private DBObject createDBObject(int count, FeedbackVO feedback) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		docBuilder.append("_id", count);
		docBuilder.append("Stars", feedback.getStar());
		docBuilder.append("Name", feedback.getName());
		docBuilder.append("Comment", feedback.getComment());
		
		return docBuilder.get();
	}

}
