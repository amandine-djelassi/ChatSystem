package chatGui;

import java.io.File;

import model.User;

public interface ChatControllerToChatGui {
	public void receiveMessage(User user, String message);
	public void notifConnected(User user);
	public void notifDisconnected(User user);
	public void receiveFile(User user, File file);
	public void askReceiveFile(User user, String nameFile, int timestamp);
	public void fileResponse(User user, Boolean b, File file);

}
