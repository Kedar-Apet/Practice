package pojo;

public class Topic {
/*
 * id    | int         | NO   | PRI | NULL    | auto_increment |
| name  | varchar(30) | YES  | UNI | NULL    |                |
 */
	private int topicId;
	private String name;
	public Topic(int topicId, String name) {
		super();
		this.topicId = topicId;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", name=" + name + "]";
	}
	
}
