package ni;
import org.json.*;

public class UDPPacket {
	
	protected enum typeMessage {HELLO , BYE , MESSAGE, FILE_REQUEST, FILE_REQUEST_RESPONSE};
	private typeMessage type; //pr recevoir le num du type on marque type.ordinal

	UDPPacket(typeMessage type){
		this.type = type;
	}
	
	public JSONObject toJSON(){
		JSONObject obj = new JSONObject();
		obj.put("type", this.type.ordinal());
		return obj;		
	}
	
	public String toString(){
		return (this.toJSON().toString());
	}
	
	public int getType (){
		return this.type.ordinal();
	}
	
}

