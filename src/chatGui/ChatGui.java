package chatGui;

import java.io.*;
import java.util.List;
import chatController.*;
import model.*;

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
	
	/////////////////////////////////////// Connection ///////////////////////////////////////
	
	//The user log in 
	public void connect(String nickname){
		cv.setVisible(false);
		chatV.setVisible(true);
		ctrl.performConnect(nickname);
	}
	
	//An other user log in 
	@Override
	public void notifConnected(User user)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname()+ " s'est connecté \n");
		chatV.addToUserList(user);
	}
	
	/////////////////////////////////////// Disconnection ///////////////////////////////////////

	//The user log out
	public void disconnect(){
		ctrl.performDisconnect();
		chatV.setVisible(false);
		cv.setVisible(true);
		chatV.textReceived.setText("");
		chatV.removeAllFromUserList();
	}

	//An other user log out
	@Override
	public void notifDisconnected(User user)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() +" s'est déconnecté \n");
		chatV.removeFromUserList(user);
	}
	
	/////////////////////////////////////// Message ///////////////////////////////////////
	
	//The user send a message
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

	//The user receive a message
	@Override
	public void receiveMessage(User user, String message)
	{
		chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() + " > moi : " + message + "\n");
	}
	
	/////////////////////////////////////// File ///////////////////////////////////////
	
	//The user send a file
	public void file(File file, List<User> receiverList){
		chatV.textReceived.setText(chatV.textReceived.getText() + "moi > ");
		for(User user : receiverList){
			chatV.textReceived.setText(chatV.textReceived.getText() + user + " ");
		}
		chatV.textReceived.setText(chatV.textReceived.getText() + ": " + file.getName() + "\n");
		for(User user : receiverList){
			ctrl.askSendFile(user, file);
		}
	}
	
	//The user receive a response about the file he send
	@Override
	public void fileResponse(User user, Boolean b, File file){
		if(b){
			chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() +" a accepté votre fichier : "+file.getName()+"\n");
		}
		else{
			chatV.textReceived.setText(chatV.textReceived.getText() + user.getNickname() +" n'a pas accepté votre fichier : "+file.getName()+"\n");
		}
	}
	
	//The user receive a request about receiving a file
	public void askReceiveFile(User user, String nameFile, int timestamp){
		fileView = new FileView(this, nameFile, user, timestamp);
		fileView.setVisible(true);
	}
	
	//The user send his response about receiving a file
	public void responseFile(boolean b, String path, User user, int timestamp) {
		fileView.setVisible(false);
		if(b){
			chatV.textReceived.setText(chatV.textReceived.getText() + "vous avez accepté le fichier de " + user.getNickname()+"\n");
		}
		else{
			chatV.textReceived.setText(chatV.textReceived.getText() + "vous n'avez pas accepté le fichier de " + user.getNickname()+"\n");
		}
		ctrl.reponseFile(b, path, user, timestamp);
	}
	
	//The user receive the file he accept 
	public void receiveFile(User user, File file){
		chatV.textReceived.setText(chatV.textReceived.getText() + "vous avez recu le fichier : "+ file.getName() +" de " +user.getNickname() +"\n");
	}
	
	
	/////////////////////////////////////// Main ///////////////////////////////////////
	public static void main (String args[]) {
		ChatGui gui = new ChatGui();		
	}
}
