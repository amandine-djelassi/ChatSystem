package model;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Observable;

public class UserList extends Observable{
	
	private HashMap<InetAddress, User> userList;
	InetAddress address;
	
	public UserList(HashMap<InetAddress, User> userList){
		this.userList = userList;
	}
	
	public void put(User user){
		address = user.getAddress();
		userList.put(address, user);
	}
	
	public void remove(InetAddress address){
		userList.remove(address);
	}
	
	public User get(InetAddress address){
		return userList.get(address);
	}
	
	public void setChanged(){
		
	}
	
}
