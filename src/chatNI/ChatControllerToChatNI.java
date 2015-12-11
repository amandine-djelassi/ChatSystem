package chatNI;

import java.io.File;
import java.net.InetAddress;

public interface ChatControllerToChatNI {
	public void sendBye();
	public void sendMessage(InetAddress address, String message);
	public void sendHelloToOne(String nickname, InetAddress address);
	public void sendHelloToAll(String nickname);
	public void sendFileRequest(InetAddress address, File file);
	public void fileRequestResponse(InetAddress address, boolean b, int timestamp);
	public void fileRequest(InetAddress address, boolean b, int timestamp, String emplacement);
}

