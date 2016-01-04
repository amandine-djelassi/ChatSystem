package chatNI;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.JSONObject;

import chatController.ChatController;
import chatController.ChatNiToChatController;
import model.User;
 
public class ChatNI implements  FromToRemoteApp, ChatControllerToChatNI{
	
	private UDPSender sender;
	private UDPReceiver receiver;
	private ChatNiToChatController niToController;
	private String path;
	private java.io.File fichier;
	private Hashtable<Integer, File> fileList;
	
	public ChatNI (ChatController ctr){
		this.receiver = new UDPReceiver(this);
		this.sender = new UDPSender(receiver.getSocket());
		receiver.start();
		this.niToController = ctr;
		TCPServer tcps = new TCPServer(this);
		this.fileList = new Hashtable<Integer, File>();
		tcps.start();
	}
	/////////////////////////////////////// Connection ///////////////////////////////////////
	
	//The user log in 
	@Override
	public void sendHelloToAll(String nickname){
		UDPPacketHello udpHello = new UDPPacketHello(nickname, true);
		try {
			sender.sendUDP(udpHello, InetAddress.getByName("255.255.255.255"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//An other user log in 
	public void hello(String nickname, Boolean reqReply, InetAddress address){
		niToController.onHello(nickname, reqReply, address);
	}
	
	//The user send back an hello to the new user
	@Override
	public void sendHelloToOne(String nickname, InetAddress address){
		UDPPacketHello udpHello = new UDPPacketHello(nickname, false);
		sender.sendUDP(udpHello, address);
	}
	
	/////////////////////////////////////// Disconnection ///////////////////////////////////////
	
	//The user log out
	@Override
	public void sendBye(){
		UDPPacketBye udpB = new UDPPacketBye();
		try {
			sender.sendUDP(udpB, InetAddress.getByName("255.255.255.255"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//An other user log out
	public void bye(InetAddress address){
		niToController.onBye(address);
	} 
	
	/////////////////////////////////////// Message ///////////////////////////////////////
	
	//The user send a message
	@Override
	public void sendMessage(InetAddress address, String message)
	{
		UDPPacketMessage udpMessage = new UDPPacketMessage(message);	
		sender.sendUDP(udpMessage, address);
	}
	
	//The user receive a message
	public void message(InetAddress address, String message){
		niToController.onMessage(address, message);
	}		
	
	/////////////////////////////////////// File ///////////////////////////////////////
	
	//The user send a file
	@Override
	public void sendFileRequest(InetAddress add, File file)
	{
		UDPPacketFileRequest UDPFR= new UDPPacketFileRequest(file.getName());
		int timestamp = UDPFR.getTimestamp();
		fileList.put(timestamp, file);
		sender.sendUDP(UDPFR,add);
	}
	
	//The user receive a response about the file he send
	public void fileRequestResponse(InetAddress add, boolean ok, int timestamp) {
		if (fileList.containsKey(timestamp)){
			File file = fileList.get(timestamp);
			niToController.receivedFileResponse(add, ok, file);
		}
		if (fileList.containsKey(timestamp) && ok)
		{
			File file = fileList.get(timestamp);
			TCPSender tcps = new TCPSender(add,file);
			tcps.start();
			fileList.remove(timestamp);
		}		
	}
	
	//The user receive a request about receiving a file
	public void fileResponse(InetAddress add, String nameFile, int timestamp) {
		niToController.askReceiveFile(add, nameFile, timestamp);
	}
	
	//The user send his response about receiving a file
	@Override
	public void fileRequest(InetAddress address, boolean b, int timestamp, String path) {	
		if(b){
			fichier = new java.io.File(path);
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		UDPPacketFileRequestResponse UDPF = new UDPPacketFileRequestResponse(b, timestamp);
		sender.sendUDP(UDPF, address);
	} 
	
	//The user receive the file he accept 
	public void file(InetAddress add, File file){
		niToController.onFile(add, file);
	}

	//Getter 
	public File getFileToReceived(){
		return this.fichier;
	}
}

