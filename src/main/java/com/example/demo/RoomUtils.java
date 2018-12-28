package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class RoomUtils {
	private static HashMap<String, Room> rooms = new HashMap<>();
	private static HashMap<WebSocketSession, Room> clientRoom = new HashMap<>();

	public void add(WebSocketSession session, String roomName) {
		if (!rooms.containsKey(roomName)) {
			Room room = new Room(roomName);
			rooms.put(roomName, room);
			clientRoom.put(session, room);
		}
	}

	public void sendMsg(WebSocketSession session, String user, String msg) {
		ArrayList<Client> temp = rooms.get(clientRoom.get(session).getRoomName()).getClientList();
		for (Client c : rooms.get(clientRoom.get(session).getRoomName()).getClientList()) {
			System.out.println("Sending message");
			if (c.getSession().isOpen()) {
				try {
					c.getSession().sendMessage(new TextMessage(msg));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public Room getRoom(String roomName) {
		return rooms.get(roomName);
	}

	public boolean checkRoom(String roomName) {
		return rooms.get(roomName) != null;
	}

	public Room getClientRoom(String clientName) {
		return clientRoom.get(clientName);
	}

	public boolean checkClientRoom(String roomName) {
		return clientRoom.get(roomName) != null;
	}

}
