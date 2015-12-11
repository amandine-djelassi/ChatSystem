package chatNI;
import org.json.*;

public class UDPPacket {
	
	protected enum typeMessage {HELLO , BYE , MESSAGE, FILE_REQUEST, FILE_REQUEST_RESPONSE} ; //estce que c le bon nom ???
	private typeMessage type; //pr recevoir le num du type on marque type.ordinal

	public UDPPacket(typeMessage type){
		this.type = type;
	}
	

	public UDPPacket(JSONObject obj){
		this.type = typeMessage.values()[obj.getInt("type")]; //retourne un tableau qui renvoie les val dans l'ordre
	}
	
	
	public JSONObject toJSON(){
		JSONObject obj = new JSONObject();
		obj.put("type", this.type.ordinal());
		return obj;		
	}
	
	public String toString(){
		return (this.toJSON().toString());
	}
	
	public typeMessage getType (){
		return this.type;
	}
	
}

