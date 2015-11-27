package chatGui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import chatController.ChatController;
import chatController.ChatGuiToChatController;

public class ChatGui implements ChatControllerToChatGui{
	
	private ChatGuiToChatController ctrl;
	private ConnectView cv;
	private ChatView chatV;
	public String[] userList = {"coco","popo","toto","lolo","momo"};
	
	public ChatGui(){
		this.ctrl = new ChatController(this);
		//fenetre connection 
		cv = new ConnectView(this);
	}
	/*si j'appuie sur connecter -> nouvelle fenetre -> send hello*/
	
	public void connect(String nickname){
		ctrl.performConnect(nickname);
		//changer le pannel
		cv.setVisible(false);
		chatV = new ChatView(this);
	}
	
	@Override
	public void receiveMessage(String nickname, String message)
	{
		System.out.println(nickname + " envoie : " + message);
	}
	
	@Override
	public void notifConnected(String nickname)
	{
		System.out.println("Hello de : " + nickname);
	}
	
	@Override
	public void notifDisconnected(String nickname)
	{
		System.out.println("Bye de : " + nickname);
	}

	public static void main (String args[]) {
		ChatGui gui = new ChatGui();
	}
	
	public String[] getUserList(){
		return userList;
	}
	
}
