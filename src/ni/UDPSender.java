package ni;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

public class UDPSender {
	private DatagramSocket socket; 
	public final static int PORT = 8045;
	
	public UDPSender(DatagramSocket socket){
		// commentaire tant qu'on a qu'un seul main 
		try {
			socket = new DatagramSocket(PORT); 
		//	this.socket = socket;
		} catch (SocketException e) {
			System.out.println("error creation DatagramSocket in UDPSender" + e);
		}
	}
	
	public void sendUDP(UDPPacket udpPacket){
		try {
			byte b[] = udpPacket.toString().getBytes(Charset.forName("UTF-8"));
			InetAddress broadcast;
			broadcast = InetAddress.getByName("255.255.255.255");
			DatagramPacket packet = new DatagramPacket(b,b.length,broadcast,PORT);
			this.socket.send(packet);
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host Exception in sendUDP"+e);
		} catch (IOException e) {
			System.out.println("send packet failed in sendUDP" +e);
		}
		
	}
}

