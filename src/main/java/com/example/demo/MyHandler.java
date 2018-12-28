package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler{
	private static HashMap<String, Room> rooms = new HashMap<>();
	private static HashMap<WebSocketSession, Room> clientRoom = new HashMap<>();
	
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // ...
		String[] msg = message.getPayload().split("\\s+", 2);
		for(String s: msg) {
			System.out.println(s);
		}
		if(msg.length > 1 && msg[0].toLowerCase().equals("join")) {
			String roomName = msg[1];
			if (!rooms.containsKey(roomName)) {
				Room room = new Room(roomName);
				room.addClient(new Client(session));
				rooms.put(roomName, room);
				clientRoom.put(session, room);
			}
			System.out.println("add room");
		}else {
			System.out.println("break");
			ArrayList<Client> temp = rooms.get(clientRoom.get(session).getRoomName()).getClientList();
			System.out.println(temp.size());
			for (Client c : temp) {
				System.out.println("Sending message");
				if (c.getSession().isOpen()) {
					try {
						c.getSession().sendMessage(new TextMessage("{ \"user\" : \"" + msg[0] + "\", \"message\" : \"" + msg[1] + "\" }"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}	
    }

}
