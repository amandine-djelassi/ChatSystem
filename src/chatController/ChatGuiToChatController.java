package chatController;

public interface ChatGuiToChatController {
	public void performConnect (String nickname);
	public void performDisconnect();
	public void askSendMessage(String nickname, String message);
}
