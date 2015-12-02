package chatNI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import chatController.ChatController;
import chatController.ChatNiToChatController;
import model.User;
 
public class ChatNI implements  FromToRemoteApp, ChatControllerToChatNI{
	
	private UDPSender sender;
	private UDPReceiver receiver;
	private User user;
	private ChatNiToChatController niToController;
	
	public ChatNI (ChatController ctr){
		this.receiver = new UDPReceiver(this);
		this.sender = new UDPSender(receiver.getSocket());
		receiver.start();
		this.niToController = ctr;
	}
	
	public void hello(String nickname, Boolean reqReply, InetAddress address){
		user = new User(nickname, address);
		System.out.println("ChatNI - hello - nickname : "+nickname+" - reqReply : "+reqReply+" - addresse : "+address.toString());
		niToController.onHello(user);
		if (reqReply == true) {
			System.out.println("ChatNI - hello - cas reqReply = true \n");
			UDPPacketHello udpHello = new UDPPacketHello(nickname, false);
			sender.sendUDP(udpHello, address);
		}	
	}
	
	public void message(InetAddress address, String message){
		niToController.onMessage(address, message);
	}		

	public void bye(InetAddress address){
		niToController.onBye(address);
	} 
	
	@Override
	public void sendMessage(InetAddress address, String message)
	{
		UDPPacketMessage udpMessage = new UDPPacketMessage(message);	
		sender.sendUDP(udpMessage, address);
	}
	
	@Override
	public void sendHello(String nickname, Boolean ReqReply){
		if(ReqReply){
			System.out.println("ChatNI - sendHello - nickname : "+nickname+" - ReqReply : True \n");
		}
		else{
			System.out.println("ChatNI - sendHello - nickname : "+nickname+" - ReqReply : False \n");
		}
		UDPPacketHello udpHello = new UDPPacketHello(nickname, ReqReply);
		try {
			sender.sendUDP(udpHello, InetAddress.getByName("255.255.255.255"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendBye(){
		UDPPacketBye udpB = new UDPPacketBye();
		try {
			sender.sendUDP(udpB, InetAddress.getByName("255.255.255.255"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}

