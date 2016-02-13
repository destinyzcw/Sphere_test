package Sphere;

public class Message {
	
	private String message;
	private Topic topic;
	private User publisher;
	
	public Message (String message, User publisher) {
		this.message = message;
		this.publisher = publisher;
	}
	
	protected String getMessage() {
		return this.message;
	}
	
	protected User getPublisher() {
		return this.publisher;
	}
	protected Topic getTopic() {
		return this.topic;
	}
	protected void setTopic (Topic topic) {
		this.topic = topic;
	}
}
