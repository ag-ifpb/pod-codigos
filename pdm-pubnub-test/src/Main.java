import java.util.Arrays;

import org.w3c.dom.css.CSS2Properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

public class Main {
	
	private static void subscriber(PubNub pubnub){
		pubnub.addListener(new SubscribeCallback() {
	        @Override
	        public void status(PubNub pubnub, PNStatus status) {}
	        @Override
	        public void presence(PubNub pubnub, PNPresenceEventResult presence) {}
	        @Override
	        public void message(PubNub pubnub, PNMessageResult message) {
	        		//
	        		System.out.println("Recebendo mensagem");
	        		System.out.println("channel: " + message.getChannel());
	        		System.out.println("message: " + message.getMessage());
	        		System.out.println("timetoken: " + message.getTimetoken());
	        }
	    });
	    pubnub.subscribe().channels(Arrays.asList("ifpb-pdm")).execute();
	}

	public static void main(String[] args) throws InterruptedException, PubNubException {
		//
	    PNConfiguration pnConfiguration = new PNConfiguration();
	    pnConfiguration.setSubscribeKey("sub-c-2876554e-030b-11e5-897f-02ee2ddab7fe");//ao receber mensagens
	    pnConfiguration.setPublishKey("pub-c-895aaf20-3f93-43bd-aa4f-6b08d6869d96");//ao enviar mensagens
	    pnConfiguration.setSecure(false);
	    //
	    PubNub pubnub = new PubNub(pnConfiguration);
	    //
	    JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("name", "Ari Garcia 2");
	    jsonObject.addProperty("msg", "Mensagem enviada via PubNub 2");
	    //
	    pubnub.publish()
	    		.message(jsonObject)
	    		.channel("chat")
		    .async(new PNCallback<PNPublishResult>() {
		        @Override
		        public void onResponse(PNPublishResult result, PNStatus status) {
		        		System.out.println("result: "+ result.toString());
		        		System.out.println("message-uuid: " + status.getUuid());
		        		
		        }
		    });
	    //
	}
}
