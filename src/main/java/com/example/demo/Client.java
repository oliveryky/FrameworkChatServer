package com.example.demo;

import org.springframework.web.socket.WebSocketSession;

public class Client {
	private WebSocketSession session;
	
	public Client(WebSocketSession session) {
		this.session = session;
	}
	
	public WebSocketSession getSession() {
		return this.session;
	}
}
