package chatController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import chatGui.ChatControllerToChatGui;
import chatGui.ChatGui;
import chatNI.ChatControllerToChatNI;
import chatNI.ChatNI;
import model.User;
public class ChatController  implements ChatNiToChatController, ChatGuiToChatController{
	
	private Hashtable<InetAddress, User> userlist;
	private ChatControllerToChatNI ni;
	private ChatControllerToChatGui gui;
	private User user;
	
	public ChatController(ChatGui gui)
	{
		userlist = new Hashtable<InetAddress, User>();
		try {
			user = new User("localhost",InetAddress.getByName("localhost"));
		} catch (UnknownHostException e) {
			System.out.println("address doesnt exist");
			e.printStackTrace();
		}
		ni = new ChatNI(this);
		this.gui = gui;
	}
	
	public void setControllerToGui(ChatControllerToChatGui controllerToGui) 
	{
		this.gui = controllerToGui;
	}
	
	@Override
	public void onHello(User user) 
	{
		userlist.put(user.getAddress(), user);
		gui.notifConnected(user);
	}
	
	@Override
	public void onBye(InetAddress address) 
	{
		userlist.remove(address);
		gui.notifDisconnected(user);
	}
	
	@Override
	public void onMessage(InetAddress address, String message)
	{
		user = userlist.get(address);
		gui.receiveMessage(user,message);
	}
	
	@Override
	public void performConnect(String nickname)
	{
		System.out.println("ChatController - performConnect - nickname : "+nickname+"\n");
		ni.sendHello(nickname,true);
	}
	
	@Override
	public void performDisconnect()
	{
		ni.sendBye();
	}
	
	@Override
	public void askSendMessage(User user, String message)
	{
			ni.sendMessage(user.getAddress(), message);
	}
}
