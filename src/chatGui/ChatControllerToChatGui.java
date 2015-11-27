package chatGui;

public interface ChatControllerToChatGui {
	public void receiveMessage(String nickname, String message);
	public void notifConnected(String nickname);
	public void notifDisconnected(String nickname);

}
