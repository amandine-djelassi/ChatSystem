package chatNI;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.json.JSONObject;

//rajouter une fonction qui filtre notre adress ip afin de ne pas se renvoyer hello. 
public class UDPReceiver extends Thread{
	private DatagramSocket socket; 
	private ChatNI chatNI;
	public final static int PORT = 8045; 
	
	public UDPReceiver(ChatNI ni){
		this.chatNI = ni ; 
		try {
			socket = new DatagramSocket(PORT);
		} catch (SocketException e) {
			System.out.println("Datagram not created in UDPReceiver" + e);
		}
	}
	
	@Override
	public void run(){
		byte[] b = new byte[1024];
		DatagramPacket packet = new DatagramPacket(b, b.length);
		String s; 
		while(true){
			try {
				socket.receive(packet);
				s = new String(packet.getData(),0,packet.getLength());
				InetAddress add = packet.getAddress();
				JSONObject obj = new JSONObject(s);
				UDPPacket UDP_P = new UDPPacket(obj);
							
				switch (UDP_P.getType()){
				
					case HELLO:
					UDPPacketHello h = new UDPPacketHello(obj);
					System.out.println("UDPReceiver - nickname : "+h.getNickname()+" - ReqReply : "+h.getReqReply()+" - addresse : "+add+"\n");
					chatNI.hello(h.getNickname(),h.getReqReply(), add);
					break; 
					case BYE: 
					chatNI.bye(add);
					break;
					case MESSAGE: UDPPacketMessage m = new UDPPacketMessage(obj);
					chatNI.message(add,m.getMessage());
					break;
					default: System.out.print("def");
					break;	
				}
				
				
				
				//on a un string -> on peut transformer en message et le renvoyer au chat ni 
				
			} catch (IOException e) {
				System.out.println("packet not receive in UDPReceiver" + e);
			}
		}
	}
	
	public DatagramSocket getSocket() {
		return socket;
	}
	
	/*public static void main(String[] args){
		UDPReceiver receive = new UDPReceiver();
		receive.start();
		UDPSender sender = new UDPSender(receive.getSocket());
		sender.sendUDP("hello");
	}*/
	
}
	