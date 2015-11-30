package chatGui;

import model.User;

public interface ChatControllerToChatGui {
	public void receiveMessage(User user, String message);
	public void notifConnected(User user);
	public void notifDisconnected(User user);

}
