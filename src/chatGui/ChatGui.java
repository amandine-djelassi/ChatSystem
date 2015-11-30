package chatGui;

import java.util.List;

import chatController.ChatController;
import chatController.ChatGuiToChatController;
import model.User;

public class ChatGui implements ChatControllerToChatGui{
	
	private ChatGuiToChatController ctrl;
	private ConnectView cv;
	private ChatView chatV;
	
	public ChatGui(){
		this.ctrl = new ChatController(this); 
		cv = new ConnectView(this);
	}
	
	public void connect(String nickname){
		cv.setVisible(false);
		chatV = new ChatView(this);
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
		chatV.textReceived.setText(chatV.textReceived.getText() + ": " + message + "\n");
	}
	
	@Override
	public void receiveMessage(User user, String message)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() + " : " + message + "\n");
		System.out.println(user.getNickname() + " envoie : " + message);
	}
	
	@Override
	public void notifConnected(User user)
	{
		chatV.textReceived.setText(user.getNickname()+ " s'est connecté \n");
		//chatV.refreshUserList(); FAIRE UN LISTENER A LA PLACE !!!!
	}
	
	@Override
	public void notifDisconnected(User user)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() +" s'est déconnecté \n");
		System.out.println("Bye de : " + user.getNickname());
	}
	
	
	public static void main (String args[]) {
		ChatGui gui = new ChatGui();
	}

	
}
