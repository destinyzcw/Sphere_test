package Sphere;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Topic {
	
	private String title;
	
	private Set<User> publishers;
	
	private Set<User> subscribers;
	
	private List<Message> data;
	
	public Topic (String title, User publisher, Message message) {
		this.title = title;
		this.publishers = new HashSet<User>();
		this.subscribers = new HashSet<User>();
		this.data = new ArrayList<Message>();
		this.publishers.add(publisher);
		this.data.add(message);
	}
	
	protected boolean relateTo (String topicName) {
		return this.title.equals(topicName);
	}
	
	protected void publish (Message message) {
		this.publishers.add(message.getPublisher());
		this.data.add(message);
	}
	
	protected void addSubscriber (User subscriber) {
		this.subscribers.add(subscriber);
	}
	
	protected void sendToSubscribers (Message message) {
		for(User user : this.subscribers){
			user.receiveMessage(message);
		}
	}
	
	protected String getTitle(){
		return this.title;
	}
	
	protected List<Message> getData(){
		return this.data;
	}
	
	protected int getDataSize(){
		return data.size();
	}
	
	protected boolean hasSubscriber(User user){
		return this.subscribers.contains(user);
	}
}
