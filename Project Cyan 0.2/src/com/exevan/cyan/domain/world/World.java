package com.exevan.cyan.domain.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.exevan.cyan.domain.entity.Entity;
import com.exevan.cyan.domain.world.map.MapGenerator;
import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.observer.Observable;
import com.exevan.cyan.framework.observer.Observer;

public class World implements Observable {
	
	private Tile[][] map;
	private List<Entity> entities;
	
	public World(long seed, int width, int height) {
		map = MapGenerator.generateMap(seed, width, height);
		entities = new ArrayList<Entity>();
	}
	
	public Tile[][] getMap() {
		return map;
	}
	
	public int getWidth() {
		return map.length;
	}
	
	public int getHeight() {
		return map[0].length;
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
