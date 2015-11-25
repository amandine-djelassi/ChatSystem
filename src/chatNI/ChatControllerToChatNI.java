package chatNI;

import java.net.InetAddress;

public interface ChatControllerToChatNI {
	public void sendHello(String nickname, Boolean ReqReply);
	public void sendBye();
	public void sendMessage(InetAddress address, String message);
}

