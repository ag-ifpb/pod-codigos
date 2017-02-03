package ag.ifpb.pod.ms;

public class Message {
	private String identify;
	private String publisherId;
	private String subscriberId;
	private String text;
	
	public Message(String identify, String publisherId, String subscriberId, String text) {
		this.identify = identify;
		this.publisherId = publisherId;
		this.subscriberId = subscriberId;
		this.text = text;
	}
	
	public String getIdentify() {
		return identify;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public String getText() {
		return text;
	}
	
}
