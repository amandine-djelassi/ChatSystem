package ni;

//ne pas importer java.net dans ChatNI pour avoir un code "propre"  
public class ChatNI implements FromToRemoteAp, ChatControllerToChatNI{
	
	private UDPSender sender;
	private UDPReceiver receiver;
	
	public ChatNI(){
		this.receiver = new UDPReceiver();
		receiver.start();
		this.sender = new UDPSender(receiver.getSocket());
	}
	
	@Override //recevoir un hello
	public void hello(String nickname, Boolean ReqReply){
		
	}
	
	@Override
	public void bye(){
		
	}
	
	@Override // envoyer un hello
	public void sendHello(String nickname, Boolean ReqReply){
		UDPPacketHello udpHello = new UDPPacketHello(nickname, ReqReply);
		sender.sendUDP(udpHello);
	}
	
	@Override
	public void sendBye(){
		//TODO par Laure vendredi, idem sendHello + faire classe UDPPacketBye 
	}
	
	public void receiveMessage(UDPPacket message){
		int type = message.getType();
		/*switch(type){
			case 0 : hello()
				break;
			default : 
				break;
		}*/
	}
	 
}

