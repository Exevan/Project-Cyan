package com.exevan.cyan.domain.world;

import com.exevan.cyan.domain.entity.Entity;
import com.exevan.cyan.domain.util.Position;

public class EntityTree {

	private class Enode {

		public Enode left, right;
		public Entity entity;

		public Enode(Enode left, Enode right, Entity entity) {
			this.left = left;
			this.right = right;
			this.entity = entity;
		}
	}

	private Enode root;

	public void insert(Entity entity) {
		root = insert(entity, root);
	}

	private Enode insert(Entity entity, Enode node) {
		if(node == null)
			return new Enode(null, null, entity);		
		if(smaller(entity.getPos(), node.entity.getPos()))
			node.left = insert(entity, node.left);
		else
			node.right = insert(entity, node.right);
		return node;
	}
	
	public void remove(Entity entity) {
		root = remove(entity, root);
	}
	
	private Enode remove(Entity entity, Enode node) {
		//google BST node removal
		if(smaller(entity.getPos(), node.entity.getPos()))
			node.left = remove(entity, node.left);
		else
			node.right = remove(entity, node.right);
		return node;
	}
	
	private boolean smaller(Position one, Position two) {
		return one.compareTo(two) < 0;
	}
}