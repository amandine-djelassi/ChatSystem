package chatController;

import model.User;

public interface ChatGuiToChatController {
	public void performConnect (String nickname);
	public void performDisconnect();
	public void askSendMessage(User user, String message);
}
