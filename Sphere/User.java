package Sphere;
import java.util.List;

public class User {
	
	private String userName;
	
	private List<Topic> subscribedTopics;
	
	private List<Message> publishedMessage;
	
	public User (String userName) {
		this.userName = userName;
	}
	
	protected String getUserName() {
		return this.userName;
	}
	
	protected List<Topic> getSubscribedTopics() {
		return this.subscribedTopics;
	}
	
	protected List<Message> getPublishedMessage() {
		return this.publishedMessage;
	}
	
	protected void publish (InfoSystem system, String topicName, Message message) {
		if(!system.containTopic(topicName)){
			Topic topic = new Topic(topicName, this, message);
			message.setTopic(topic);
			system.addTopic(topic);
		}
		else{
			system.updateTopic(topicName, message);
		}
	}
	
	protected void receiveMessage (Message message) {
		StringBuilder sb = new StringBuilder();
		sb.append(userName);
		sb.append(" received ");
		sb.append(message.getMessage());
		sb.append(" of " + message.getTopic().getTitle());
		sb.append(" from: ");
		sb.append(message.getPublisher().getUserName());
		System.out.println(sb.toString());
	}
	
	protected void receiveMessage (InfoSystem system, String topicName, int index) {
		
		if(!system.containTopic(topicName))
			System.out.println(topicName + " doesn't exist!");
		else{
			Topic topic = system.getTopic(topicName);
			if(!topic.hasSubscriber(this)) {
				System.out.println("You haven't subscribed " + topicName + " yet!");
				return;
			}
			int size = topic.getDataSize();
			if(size < index) 
				System.out.println("Only have " + topic.getDataSize() + " messages!");
			else{
				List<Message> data = topic.getData();
				for(int i = index - 1; i < size; i++){
					StringBuilder sb = new StringBuilder();
					Message message = data.get(i);
					sb.append(userName);
					sb.append(" received ");
					sb.append(message.getMessage());
					sb.append(" of " + message.getTopic().getTitle());
					sb.append(" from: ");
					sb.append(message.getPublisher().getUserName());
					System.out.println(sb.toString());
				}
			}
		}
		
	}
	
	protected boolean subscrible (InfoSystem system, String topicName) {
		if(system.containTopic(topicName)){
			Topic topic = system.getTopic(topicName);
			topic.addSubscriber(this);
			return true;
		}
		else return false;
	}

	
}
