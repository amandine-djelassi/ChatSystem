import ni.*;
public class TestHello {
	public static void main(String[] args){
		UDPReceiver receive = new UDPReceiver();
		receive.start();
		UDPSender sender = new UDPSender(receive.getSocket());
	//	UDPPacketHello hello = new UDPPacketHello("laure", true);
	//	sender.sendUDP(hello);
		
	}
}
