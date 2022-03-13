package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Topic;
import utils.DBUtils;

public class TopicDaoImpl implements ITopicDao {
	private Connection cn;
	private PreparedStatement pst1;
	
	public TopicDaoImpl() throws SQLException {
	cn=DBUtils.fetchDBCOnnection();
	pst1=cn.prepareStatement("select * from topics");
	}
	@Override
	public List<Topic> getAllTopics() throws SQLException {
		List<Topic> topicList=new ArrayList<>();
		try(ResultSet rst=pst1.executeQuery()){
		while(rst.next()) {
			topicList.add(new Topic(rst.getInt(1),rst.getString(2)));
		}
	}
		return topicList ;
	}
	public void cleanUp() throws SQLException {
		if(pst1!=null) {
			pst1.close();
		}
	}

}
