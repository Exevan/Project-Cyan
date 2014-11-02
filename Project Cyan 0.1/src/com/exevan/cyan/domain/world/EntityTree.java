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
		remove(entity, root);
	}

	private void remove(Entity entity, Enode node) {
		if(node == null)
			return;
		
		if(entity.getPos().equals(node.entity.getPos())) {
			Enode next = findNextNode(node);
			if (next == null)
				node = node.left;
			else
				node = next;
		}
		
		if(smaller(entity.getPos(), node.entity.getPos()))
			remove(entity, node.left);	
		else
			remove(entity, node.right);	
	}
	
	private void replaceWithNext(Enode node) {
		
		
	}
	
	private Enode findNextNode(Enode node) {
		if(node.right == null)
			return null;
		Enode next = node.right;
		while(next.left != null)
			next = next.left;
		return next;
	}

	private boolean smaller(Position one, Position two) {
		return one.compareTo(two) <= 0;
	}
}