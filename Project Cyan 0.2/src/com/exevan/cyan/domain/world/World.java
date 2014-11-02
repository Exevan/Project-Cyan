package com.exevan.cyan.domain.world;

import java.util.ArrayList;
import java.util.Collection;

import com.exevan.cyan.domain.world.map.MapGenerator;
import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.observer.Observable;
import com.exevan.cyan.framework.observer.Observer;

public class World implements Observable {
	
	private double[][] map;
	
	public World(long seed, int width, int height) {
		map = MapGenerator.generateMap(seed, width, height);
	}
	
	public double[][] getMap() {
		return map;
	}

	private Collection<Observer> observers = new ArrayList<Observer>();

	@Override
	public void register(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unregistrer(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void dispatchEvent(Event e) {
		for (Observer observer : observers) {
			observer.handle(e);
		}
	}
}
