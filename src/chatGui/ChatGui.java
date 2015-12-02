package chatGui;

import java.util.List;

import chatController.ChatController;
import chatController.ChatGuiToChatController;
import model.User;
import model.UserList;

public class ChatGui implements ChatControllerToChatGui{
	
	private ChatGuiToChatController ctrl;
	private ConnectView cv;
	private ChatView chatV;
	private List<User> userList;
	
	public ChatGui(){
		this.ctrl = new ChatController(this); 
		cv = new ConnectView(this);
		chatV = new ChatView(this);
		chatV.setVisible(false);
		userList = null;
		
	}
	
	public void connect(String nickname){
		System.out.println("ChatGui - connect - nickname : " + nickname+"\n");
		cv.setVisible(false);
		chatV.setVisible(true);
		ctrl.performConnect(nickname);
	}
	
	public void disconnect(){
		ctrl.performDisconnect();
		chatV.setVisible(false);
		cv.setVisible(true);
	}
	
	public void message(String message, List<User> user){ 
		for(int i=0; i<user.size(); i++){
			ctrl.askSendMessage(user.get(i), message);
			chatV.textReceived.setText(chatV.textReceived.getText() + user.get(i) + " ");
		}
		chatV.textReceived.setText(chatV.textReceived.getText() + ":" + message + "\n");
	}
	
	@Override
	public void receiveMessage(User user, String message)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + "moi - " + user.getNickname() + " : " + message + "\n");
	}
	
	@Override
	public void notifConnected(User user)
	{
		chatV.textReceived.setText(user.getNickname()+ " s'est connecté \n");
		chatV.addToUserList(user);
	}
	
	@Override
	public void notifDisconnected(User user)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() +" s'est déconnecté \n");
		chatV.removeFromUserList(user);
	}
	
	
	public static void main (String args[]) {
		ChatGui gui = new ChatGui();
	}

	
}
