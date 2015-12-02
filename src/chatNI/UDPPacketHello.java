package chatNI;
import org.json.JSONObject;

public class UDPPacketHello extends UDPPacket{

	private boolean reqReply;
	private String nickname;
	
	UDPPacketHello(String nickname, boolean reqReply) {
		super(typeMessage.HELLO);
		this.reqReply = reqReply;
		this.nickname = nickname;
	}
	public boolean getReqReply()
	{
		return this.reqReply;
	}
	
	public String getNickname()
	{
		return this.nickname;
	}
	
	public UDPPacketHello(JSONObject obj){
		super(obj);
		this.nickname = obj.getString("nickname");
		this.reqReply = obj.getBoolean("reqReply");
	}
	
	@Override 
	public JSONObject toJSON(){
		JSONObject obj = super.toJSON();
		obj.put("nickname", this.nickname);
		obj.put("reqReply", this.reqReply);
		return obj;
	}

}
