package ni;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.json.JSONObject;

public class UDPReceiver extends Thread{
	private DatagramSocket socket; 
	public final static int PORT = 8045; 
	
	public UDPReceiver(){
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
				//switch case
				//case : 0
				//TODO : UDPPacketHello hello = UDPPacketHello(new JSONObject(s));
				
				System.out.println();
				
				//on a un string -> on peut transformer en message et le renvoyer au chat ni 
				//UDPPacketMessage message = new UDPPacketMessage(s);
				//receiveMessage(message);
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
