package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojo.Topic;

class TestTopicDaoImpl {
private TopicDaoImpl topicDao;
	@BeforeEach
	void setUp() throws Exception {
		topicDao=new TopicDaoImpl();
	}

	@Test
	void testGetAllTopics() throws SQLException {
		List<Topic> allTopics = topicDao.getAllTopics();
		topicDao.cleanUp();
		assertEquals(4, allTopics.size());
	}

}
