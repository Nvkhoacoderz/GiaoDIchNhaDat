package com.giaodichnhadat.presentation;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
	private List<GDSubscriber> subscribers = new ArrayList<GDSubscriber>();
	
	public void registerSubscriber(GDSubscriber subscriber) {
		subscribers.add(subscriber);
	}
	
	public void notifySubscriber() {
		for (GDSubscriber gdSubscriber : subscribers) {
			gdSubscriber.show();
		}
	}
}
