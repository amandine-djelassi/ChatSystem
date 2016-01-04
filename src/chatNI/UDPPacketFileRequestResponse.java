package chatNI;

import org.json.JSONObject;

import chatNI.UDPPacket.typeMessage;

public class UDPPacketFileRequestResponse extends UDPPacket{
	private boolean ok;
	int timestamp;
	
	public UDPPacketFileRequestResponse(boolean ok,int timestamp)
	{
		super(typeMessage.FILE_REQUEST_RESPONSE);
		this.ok=ok;
		this.timestamp=timestamp;
		
	}
	public UDPPacketFileRequestResponse(JSONObject obj){
		super(obj);		
		this.ok = obj.getBoolean("ok");
		this.timestamp = obj.getInt("timestamp");
	}
	public boolean getOk()
	{
		return this.ok;
	}
	public int getTimestamp()
	{
		return this.timestamp;
	}
	
	public JSONObject toJSON(){
		JSONObject obj = super.toJSON();
		obj.put("ok", this.ok);
		obj.put("timestamp",this.timestamp);
		return obj;
	}
	
	


}
