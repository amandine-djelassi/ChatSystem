package ni;
import org.json.JSONObject;

public class UDPPacketHello extends UDPPacket{

	private boolean reqReply;
	private String nickname;
	
	UDPPacketHello(String nickname, boolean reqReply) {
		super(typeMessage.HELLO);
		this.reqReply = reqReply;
		this.nickname = nickname;
	}
	
	public void JSONObject(String s){
		JSONObject obj = new JSONObject(s);
		this.nickname = obj.getString("nickname");
		this.reqReply = obj.getBoolean("reqReply");
	}
	
	//faire les getter de nickname et reqReply pour pouvoir les utiliser dans UDPReceiver 
	
	@Override 
	public JSONObject toJSON(){
		JSONObject obj = super.toJSON();
		obj.put("nickname", this.nickname);
		obj.put("reqReply", this.reqReply);
		return obj;
	}

}
