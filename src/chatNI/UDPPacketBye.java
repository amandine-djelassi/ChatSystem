package chatNI;

import org.json.JSONObject;

public class UDPPacketBye extends UDPPacket{
	
	public UDPPacketBye()
	{
		super(typeMessage.BYE);
	}
	
	public UDPPacketBye(JSONObject obj)
	{
		super(obj);
	}

}
