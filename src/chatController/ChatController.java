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

public class ChatController implements ChatNiToChatController, ChatGuiToChatController{
	static Hashtable<String, String> userlist;
	private ChatControllerToChatNI ni;
	private ChatControllerToChatGui gui;
	
	//est-ce qu'on peut mettre dans le sd refreshlist 
	public ChatController(ChatGui gui){
		userlist = new Hashtable<String, String>();
		userlist.put("localhost", "/127.0.0.1");
		userlist.put("/127.0.0.1","localhost");
		ni = new ChatNI(this);
		this.gui = gui;
	}
	
	//permet de tester le main 
	public void setControllerToGui(ChatControllerToChatGui controllerToGui) {
		this.gui = controllerToGui;
	}
	
	@Override
	public void onHello(String nickname,InetAddress address) {
		userlist.put(nickname, address.toString());
		userlist.put(address.toString(),nickname);
		gui.notifConnected(nickname);
	}
	
	@Override
	public void onBye(InetAddress address) {
		String nickname = getNickname(userlist.get(address.toString()));
		userlist.remove(address.toString());
		userlist.remove(nickname);
		gui.notifDisconnected(nickname);
	}
	
	@Override
	public void onMessage(InetAddress address, String message)
	{
		String add = address.toString();
		gui.receiveMessage(getNickname(add),message);
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
	public void askSendMessage(String nickname, String message)
	{
		try {
			ni.sendMessage(getAdresse(nickname), message);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNickname(String add){
		return (userlist.get(add.toString()));
	}
	
	public InetAddress getAdresse(String nickname) throws UnknownHostException{
		String addresse = userlist.get(nickname);
		return (InetAddress.getByName(addresse));
	}

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
