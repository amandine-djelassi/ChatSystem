package chatController;

import java.net.InetAddress;

public interface ChatNiToChatController {

	public void onHello (String nickname, InetAddress address);
	public void onBye (InetAddress address);
	public void onMessage(InetAddress address, String message);
	
}
