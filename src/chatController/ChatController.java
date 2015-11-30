package chatController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import chatGui.ChatControllerToChatGui;
import chatGui.ChatGui;
import chatNI.ChatControllerToChatNI;
import chatNI.ChatNI;
import model.User;

public class ChatController implements ChatNiToChatController, ChatGuiToChatController{
	static Hashtable<InetAddress, User> userlist;
	private ChatControllerToChatNI ni;
	private ChatControllerToChatGui gui;
	private User user;
	
	//est-ce qu'on peut mettre dans le sd refreshlist 
	public ChatController(ChatGui gui){
		userlist = new Hashtable<InetAddress, User>();
		try {
			user = new User("localhost",InetAddress.getByName("localhost"));
			userlist.put(InetAddress.getByName("localhost"),user);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ni = new ChatNI(this);
		this.gui = gui;
	}
	
	//permet de tester le main 
	public void setControllerToGui(ChatControllerToChatGui controllerToGui) {
		this.gui = controllerToGui;
	}
	
	@Override
	public void onHello(User user) {
		userlist.put(user.getAddress(), user);
		gui.notifConnected(user);
	}
	
	@Override
	public void onBye(InetAddress address) {
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
		ni.sendHello(nickname,false);
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
	/*
	public String getNickname(String add){
		return (userlist.get(add.toString()));
	}
	
	public InetAddress getAdresse(String nickname) throws UnknownHostException{
		String addresse = userlist.get(nickname);
		return (InetAddress.getByName(addresse));
	}
*/
	/*
	public static void main (String args[]) {
		ChatController ctr = new ChatController();
		ctr.performConnect("coco");		
		ctr.askSendMessage("coco","jespere que ca va marcher");
		ctr.setControllerToGui(new ChatControllerToChatGui(){
			
			@Override
			public void receiveMessage(String nickname, String message)
			{
				System.out.println(nickname + " envoie : " + message);
			}
			@Override
			public void notifConnected(String nickname)
			{
				System.out.println("Hello de : " + nickname);
				/*Set keySet =userlist.keySet();

				Iterator it= keySet.iterator();

				while (it.hasNext()){
					Object key =it.next();
					System.out.println("clé : "+key+" - valeur : "+ userlist.get(key));
				}*/
		/*	}
			@Override
			public void notifDisconnected(String nickname)
			{
				System.out.println("Bye de : " + nickname);
			}
			
		});
	}*/
}
