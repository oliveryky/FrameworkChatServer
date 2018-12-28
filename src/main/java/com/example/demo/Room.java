package com.example.demo;

import java.util.ArrayList;

public class Room {
	private ArrayList<Client> clients;
	private String roomName;
	
	public Room(String roomName) {
		clients = new ArrayList<>();
		this.roomName = roomName;
	}
	
	public String getRoomName() {
		return this.roomName;
	}
	
	public void addClient(Client client) {
		clients.add(client);
	}
	
	public ArrayList<Client> getClientList() {
		return clients;
	}
}
