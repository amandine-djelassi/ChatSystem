package chatNI;

import java.net.InetAddress;
import java.net.UnknownHostException;

import chatController.ChatController;
import chatController.ChatNiToChatController;

//ne pas importer java.net dans ChatNI pour avoir un code "propre"  
public class ChatNI implements  FromToRemoteApp, ChatControllerToChatNI{
	
	private UDPSender sender;
	private UDPReceiver receiver;
	
	private ChatNiToChatController niToController;
	
	public ChatNI (ChatController ctr){
		this.receiver = new UDPReceiver(this);
		this.sender = new UDPSender(receiver.getSocket());
		receiver.start();
		this.niToController = ctr;
	}
	
	//dire au controller qu'on a reçu un hello
	public void hello(String nickname, Boolean reqReply, InetAddress address){
		niToController.onHello(nickname, address);
		if (reqReply == true) {
			sendHello("reponse",false); //reponse  = nickname de a qui on envoie
		}	
	}
	
	public void message(InetAddress address, String message)
	{
		niToController.onMessage(address, message);
	}		
	

	public void bye(InetAddress address){
		niToController.onBye(address) ; 
		
	}
	
	@Override
	public void sendMessage(InetAddress address, String message)
	{
		UDPPacketMessage udpMessage = new UDPPacketMessage(message);	
		sender.sendUDP(udpMessage, address);
	}
	
	@Override
	public void sendHello(String nickname, Boolean ReqReply){
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
		
	/*
	public static void main (String args[]) {
		ChatNI ni = new ChatNI();
		try {
		ni.sendHello("coco", true);
		
		ni.sendMessage(InetAddress.getByName("255.255.255.255") , "jespere que ca va marcher");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ni.setNiToController(new ChatNiToChatController() {
			
			@Override
			public void onHello(String nickname, InetAddress address) {
				System.out.println("hello");
				
			}
			
			@Override
			public void onBye(InetAddress address) {
				System.out.println("bye");
				
			}
			
			@Override
			public void onMessage(InetAddress address, String message)
			{
				System.out.println(message);
			}
		});
		
	}*/

	 
}

