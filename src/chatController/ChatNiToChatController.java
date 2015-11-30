package chatController;

import java.net.InetAddress;

import model.User;

public interface ChatNiToChatController {

	public void onBye(InetAddress address);
	public void onHello(User user);
	public void onMessage(InetAddress address, String message);
	
}
