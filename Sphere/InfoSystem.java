package Sphere;

import java.util.HashSet;
import java.util.Set;

public class InfoSystem {
	
	private String name;
	
	private Set<Topic> topics;
	
	public InfoSystem (String name) {
		this.name = name;
		this.topics = new HashSet<Topic>();
	}
	
	public Topic getTopic (String topicName) {
		for(Topic t : topics){
			if(t.relateTo(topicName)) return t;
		}
		return null;
	}
	
	public void addTopic (Topic topic) {
		this.topics.add(topic);
	}
	
	public boolean containTopic (String topicName) {
		for(Topic t : topics){
			if(t.relateTo(topicName)) return true;
		}
		return false;
	}
	
	public void updateTopic (String topicName, Message message) {
		
		for(Topic t : topics){
			if(t.relateTo(topicName)){
				message.setTopic(t);
				t.publish(message);
			}
		}
	}
	
	public void publish (User publisher, String topicName, String s) {
		Message message = new Message(s, publisher);
		publisher.publish(this, topicName, message);
		Topic topic = getTopic(topicName);
		topic.sendToSubscribers(message);
		publisher.subscrible(this, topicName);
	}
	
	public void subscribe (User subscriber, String topicName) {
		subscriber.subscrible(this, topicName);
	}
	
	public void receiveMessage (User user, String topicName, int index) {
		user.receiveMessage(this, topicName, index);
	}
	
	public static void main (String[] args){
		
		InfoSystem infoSystem = new InfoSystem("test");
		User user1 = new User("Alice");
		User user2 = new User("Bob");
		User user3 = new User("Chirs");
		
		System.out.println("1.");
		infoSystem.publish(user1, "topic1", "message1");
		infoSystem.subscribe(user2, "topic1");
		
		System.out.println("2.");
		infoSystem.publish(user3, "topic1", "message2");
		
		System.out.println("3.");
		infoSystem.publish(user3, "topic2", "message3");
		
		System.out.println("4.");
		infoSystem.receiveMessage(user2, "topic1", 1);
		
		System.out.println("5.");
		infoSystem.receiveMessage(user2, "topic1", 3);
		
		System.out.println("6.");
		infoSystem.receiveMessage(user2, "topic2", 1);
		
		System.out.println("7.");
		infoSystem.receiveMessage(user1, "topic1", 1);
	}
}
