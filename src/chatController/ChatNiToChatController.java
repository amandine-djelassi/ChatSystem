package chatController;

import java.io.File;
import java.net.InetAddress;

import model.User;

public interface ChatNiToChatController {

	public void onBye(InetAddress address);
	public void onMessage(InetAddress address, String message);
	public void onHello(String nickname, Boolean reqReply, InetAddress address);
	public void onFile(InetAddress add, File file);
	public void askReceiveFile(InetAddress add, String nameFile, int timestamp);
	
}
