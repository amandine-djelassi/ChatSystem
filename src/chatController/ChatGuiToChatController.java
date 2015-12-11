package chatController;

import java.io.File;

import model.User;

public interface ChatGuiToChatController {
	public void performConnect (String nickname);
	public void performDisconnect();
	public void askSendMessage(User user, String message);
	public void askSendFile(User user, File file);
	public void reponseFile(boolean b, String emplacement, User user, int timestamp);
}
