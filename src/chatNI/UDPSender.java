package chatNI;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

public class UDPSender {
	private DatagramSocket socket;

	public UDPSender(DatagramSocket socket) {
		this.socket = socket;
	}

	public void sendUDP(UDPPacket udpPacket, InetAddress address) {
		try {
			byte[] b = udpPacket.toString().getBytes(Charset.forName("UTF-8"));
			DatagramPacket packet = new DatagramPacket(b, b.length, address, UDPReceiver.PORT);
			this.socket.send(packet);
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host Exception in sendUDP" + e);
		} catch (IOException e) {
			System.out.println("send packet failed in sendUDP" + e);
		}

	}
}
