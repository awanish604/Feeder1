package com.ulok.feedback.dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoServerException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.WriteResult;
import com.ulok.feedback.constants.DBConstants;
import com.ulok.feedback.interfaces.IFeedbackDao;
import com.ulok.feedback.utils.DBUtils;
import com.ulok.feedback.vo.FeedbackVO;

public class MongoFeedbackDao implements IFeedbackDao {
	private MongoClient client = null;

	@Override
	public int postFeedback(FeedbackVO feedback) {
		System.out.println(">>>>inside Mongo Feedback");

		int result = 0;
		
		try {
			//get MongoDB client named localhost running in port 27017
			client = new MongoClient(DBConstants.LOCAL_HOST, DBConstants.MONGODB_PORT_NUMBER);
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
			writeResult = col.insert(feedbackDoc);
			result = 1;
			
			System.out.println("write result:"+writeResult);
			
		}catch(MongoServerException e) {
			result = 0;
			System.out.println("MongoServerException:"+e);
			e.printStackTrace();
		}catch(MongoTimeoutException ex) {
			System.out.println("Timeout exception:"+ex);
			ex.printStackTrace();
		}catch(Exception e) {
			System.out.println("Unknown exception:"+e);
			e.printStackTrace();
		}
		finally {
			//close resources
			DBUtils.releaseResource(client);
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
