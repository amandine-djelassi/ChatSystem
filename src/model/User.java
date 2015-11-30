package model;

import java.net.InetAddress;

public class User {
	
	private String nickname;
	private InetAddress address;
	
	public User(String nickname, InetAddress address){
		this.nickname = nickname;
		this.address = address;
	}
	
	public String getNickname(){
		return this.nickname;
	}
	
	public InetAddress getAddress(){
		return this.address;
	}
	
	public String toString(){
		return this.nickname;
	}
}
