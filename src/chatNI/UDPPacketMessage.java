package chatNI;

import org.json.JSONObject;


public class UDPPacketMessage extends UDPPacket {
	
	private String message;
	public UDPPacketMessage(String mess){
		super(typeMessage.MESSAGE);
		this.message = mess;
				
	}
	public UDPPacketMessage(JSONObject obj){
		super(obj);		
		this.message = obj.getString("message");
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public JSONObject toJSON(){
		JSONObject obj = super.toJSON();
		obj.put("message", this.message);
	
		return obj;
	}
	

}
