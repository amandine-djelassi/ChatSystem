package chatGui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JFileChooser;

import chatController.ChatController;
import chatController.ChatGuiToChatController;
import model.User;
import model.UserList;

public class ChatGui implements ChatControllerToChatGui{
	
	private ChatGuiToChatController ctrl;
	private ConnectView cv;
	private ChatView chatV;
	private FileView fileView;

	public ChatGui(){
		this.ctrl = new ChatController(this); 
		cv = new ConnectView(this);
		chatV = new ChatView(this);
		chatV.setVisible(false);
	}
	
	public void connect(String nickname){
		cv.setVisible(false);
		chatV.setVisible(true);
		ctrl.performConnect(nickname);
	}
	
	public void disconnect(){
		ctrl.performDisconnect();
		chatV.setVisible(false);
		cv.setVisible(true);
		chatV.textReceived.setText("");
		chatV.removeAllFromUserList();
	}
	
	public void message(String message, List<User> receiverList){ 
		chatV.textReceived.setText(chatV.textReceived.getText() + "moi > ");
		for(User user : receiverList){
			chatV.textReceived.setText(chatV.textReceived.getText() + user + " ");
		}
		chatV.textReceived.setText(chatV.textReceived.getText() + ": " + message + "\n");
		for(User user : receiverList){
			ctrl.askSendMessage(user, message);
		}
	}
	
	public void file(File file, List<User> receiverList){
		for(User user : receiverList){
			chatV.textReceived.setText(chatV.textReceived.getText() + user + " ");
		}
		chatV.textReceived.setText(chatV.textReceived.getText() + ": " + file.getName() + "\n");
		for(User user : receiverList){
			ctrl.askSendFile(user, file);
		}
	}
	
	@Override
	public void receiveMessage(User user, String message)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() + " > moi : " + message + "\n");
	}
	
	@Override
	public void notifConnected(User user)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname()+ " s'est connecté \n");
		chatV.addToUserList(user);
	}
	
	@Override
	public void notifDisconnected(User user)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() +" s'est déconnecté \n");
		chatV.removeFromUserList(user);
	}
	
	public void receiveFile(User user, File file){
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() +" vous a envoyé le fichier : " + file.getName() +"\n");
	}
		
	public static void main (String args[]) {
		ChatGui gui = new ChatGui();		
	}
	
	public void askReceiveFile(User user, String nameFile, int timestamp){
		fileView = new FileView(this, nameFile, user, timestamp);
		fileView.setVisible(true);
	}

	public void responseFile(boolean b, String path, User user, int timestamp) {
		fileView.setVisible(false);
		ctrl.reponseFile(b, path, user, timestamp);
		
	}
}
