package chatController;

import java.io.File;
import java.net.*;
import java.util.Hashtable;
import chatGui.*;
import chatNI.*;
import model.User;


public class ChatController  implements ChatNiToChatController, ChatGuiToChatController{
	
	private Hashtable<InetAddress, User> userlist;
	private ChatControllerToChatNI ni;
	private ChatControllerToChatGui gui;
	private User currentUser;
	
	public ChatController(ChatGui gui)
	{
		userlist = new Hashtable<InetAddress, User>();
		ni = new ChatNI(this);
		this.gui = gui;
	}
	
	public void setControllerToGui(ChatControllerToChatGui controllerToGui) 
	{
		this.gui = controllerToGui;
	}
	
	/////////////////////////////////////// Connection ///////////////////////////////////////

	//The user log in 
	@Override
	public void performConnect(String nickname)
	{
		try {
			currentUser = new User(nickname, InetAddress.getByName("localhost"));
			userlist.put(currentUser.getAddress(), currentUser);
		} catch (UnknownHostException e) {
			System.out.println("address doesnt exist");
		}
		ni.sendHelloToAll(nickname);
	}
	
	//An other user log in 
	@Override
	public void onHello(String nickname, Boolean reqReply, InetAddress address) 
	{
		User tempUser;
		if(reqReply && !nickname.equals(currentUser.getNickname())){
			ni.sendHelloToOne(currentUser.getNickname(), address);
		}
		tempUser = new User(nickname, address);
		userlist.put(tempUser.getAddress(), tempUser);
		gui.notifConnected(tempUser);
	}
	
	/////////////////////////////////////// Disconnection ///////////////////////////////////////
	
	//The user log out
	@Override
	public void performDisconnect()
	{
		ni.sendBye();
	}
	
	//An other user log out
	@Override
	public void onBye(InetAddress address) 
	{
		User tempUser;
		tempUser = userlist.get(address);
		userlist.remove(address);
		gui.notifDisconnected(tempUser);
	}
	
	/////////////////////////////////////// Message ///////////////////////////////////////
	
	//The user send a message
	@Override
	public void askSendMessage(User user, String message)
	{
		ni.sendMessage(user.getAddress(), message);
	}
	
	//The user receive a message
	@Override
	public void onMessage(InetAddress address, String message)
	{
		User tempUser;
		tempUser = userlist.get(address);
		gui.receiveMessage(tempUser,message);
	}
	
	/////////////////////////////////////// File ///////////////////////////////////////
	
	//The user send a file
	public void askSendFile(User user, File file)
	{
		ni.sendFileRequest(user.getAddress(),file);
	}
	
	//The user receive a response about the file he send
	@Override
	public void receivedFileResponse(InetAddress add, boolean ok, File file) {
		User tempUser;
		tempUser = userlist.get(add);
		gui.fileResponse(tempUser, ok, file);		
	}
	
	//The user receive a request about receiving a file
	public void askReceiveFile(InetAddress add, String nameFile, int timestamp){
		User tempUser;
		tempUser = userlist.get(add);
		gui.askReceiveFile(tempUser, nameFile, timestamp);
	}

	//The user send his response about receiving a file
	@Override
	public void reponseFile(boolean b, String path, User user, int timestamp) {
		ni.fileRequest(user.getAddress(), b, timestamp, path);
	}
	
	//The user receive the file he accept 
	public void onFile(InetAddress address, File file){
		User tempUser;
		tempUser = userlist.get(address);
		gui.receiveFile(tempUser, file);
	}
}
