package chatNI;

import org.json.JSONObject;

public class UDPPacketFileRequest extends UDPPacket {

	private String nameFile;
	int timestamp;
	public UDPPacketFileRequest(String nameFile)
	{
		super(typeMessage.FILE_REQUEST);
		this.nameFile=nameFile;
		this.timestamp= (int) System.currentTimeMillis();
		
	}
	public UDPPacketFileRequest(JSONObject obj){
		super(obj);		
		this.nameFile = obj.getString("name");
		this.timestamp = obj.getInt("timestamp");
	}
	public String getNameFile()
	{
		return this.nameFile;
	}
	public int getTimestamp()
	{
		return this.timestamp;
	}
	
	public JSONObject toJSON(){
		JSONObject obj = super.toJSON();
		obj.put("name", this.nameFile);
		obj.put("timestamp",this.timestamp);
		return obj;
	}
	
	


}
