package com.exevan.cyan.domain.world;

import java.util.Stack;

import com.exevan.cyan.domain.entity.Entity;
import com.exevan.cyan.domain.util.Position;

public class Entity2DTree {

	private class E2DNode {

		private Entity entity;
		private E2DNode left, right, parent;

		public E2DNode(Entity entity) {
			this.entity = entity;
		}

		public int getX() {
			return entity.getX();
		}

		public int getY() {
			return entity.getY();
		}

		@Override
		public boolean equals(Object o) {
			if(o == null)
				return false;
			if(o.getClass().equals(E2DNode.class)) {
				E2DNode other = (E2DNode) o;
				return this.entity.getPos().equals(other.entity.getPos());
			} else if(o.getClass().equals(Position.class)) {
				Position other = (Position) o;
				return this.entity.getPos().equals(other);
			} else if(o.getClass().equals(Entity.class)) {
				Entity other = (Entity) o;
				return this.entity.getPos().equals(other.getPos());
			} else
				return false;
		}

		@Override
		public String toString() {
			return entity.getPos().toString();
		}
	}

	private class Entity2DTreeWriter {

		public String writeTree(E2DNode node) {
			String text = "";
			Stack<E2DNode> stack = new Stack<E2DNode>();
			stack.push(node);
			while (! stack.isEmpty()) {
				node = stack.pop();
				if(node.left != null) {
					text += node.toString() + " -- " + node.left.toString() + " L\n";
					stack.push(node.left);
				}

				if(node.right != null) {
					text += node.toString() + " -- " + node.right.toString() + " R\n";
					stack.push(node.right);
				}		
			}

			return text;
		}

	}

	private int w, h;
	private E2DNode root;
	private Entity2DTreeWriter writer = new Entity2DTreeWriter();

	public Entity2DTree(int w, int h) {
		this.w = w;
		this.h = h;
	}

	public Entity find(Position pos) {
		if(! inBounds(pos))
			return null;
		return findX(pos, root);
	}

	private Entity findX(Position pos, E2DNode node) {
		if(node.equals(pos))
			return node.entity;
		else if(pos.getX() < node.getX())
			return findY(pos, node.left);
		else
			return findY(pos, node.right);
	}

	private Entity findY(Position pos, E2DNode node) {
		if(node.equals(pos))
			return node.entity;
		else if(pos.getY() < node.getY())
			return findX(pos, node.left);
		else
			return findX(pos, node.right);
	}

	public void insert(Entity entity) {
		if(! inBounds(entity.getPos()))
			return;
		root = insertX(entity, root);
	}

	private E2DNode insertX(Entity entity, E2DNode node) {
		if(node == null)
			return new E2DNode(entity);
		else if(entity.getX() < node.getX()) {
			node.left = insertY(entity, node.left);
			node.left.parent = node;
		} else {
			node.right = insertY(entity, node.right);
			node.right.parent = node;
		}
		return node;
	}

	private E2DNode insertY(Entity entity, E2DNode node) {
		if(node == null)
			return new E2DNode(entity);
		else if(entity.getY() < node.getY()) {
			node.left = insertX(entity, node.left);
			node.left.parent = node;
		} else {
			node.right = insertX(entity, node.right);
			node.right.parent = node;
		}
		return node;
	}

	public void remove(Entity entity) {
		if(root != null)
			root = removeX(entity, root, X);
	}

	private E2DNode removeX(Entity entity, E2DNode node, int cd) {
		if(node.equals(entity)) {
			E2DNode min = findMinX(node, (cd+1)%DIM);
			removeY(min.entity, min, (cd+1)%DIM);
			min.left = node.left;
			min.right = node.right;
			return min;
		}
		
		if(entity.getX() < node.getX())
			node.left = removeY(entity, node.left, (cd+1)%DIM);
		else
			node.right = removeY(entity, node.right, (cd+1)%DIM);
				
		return node;
	}

	private E2DNode removeY(Entity entity, E2DNode node, int cd) {
		if(node.equals(entity)) {
			E2DNode min = findMinY(node, (cd+1)%DIM);
			removeX(min.entity, min, (cd+1)%DIM);
			min.left = node.left;
			min.right = node.right;
			return min;
		}
		
		if(entity.getY() < node.getY())
			node.left = removeX(entity, node.left, (cd+1)%DIM);
		else
			node.right = removeX(entity, node.right, (cd+1)%DIM);
		
		return node;
	}
	
	private E2DNode findMinX(E2DNode node, int cd) {
		if(node.left == null && node.right == null)
			return node;
		
		if(cd == X && node.left != null)
			return findMinX(node.left, (cd+1)%DIM);
		else {
			if(node.left == null) return minX(node, findMinX(node.right, (cd+1)%DIM));
			else if(node.right == null) return minX(node, findMinX(node.left, (cd+1)%DIM));			
			else return minX(findMinX(node.left, (cd+1)%DIM), findMinX(node.right, (cd+1)%DIM));
		}
	}
	
	private E2DNode findMinY(E2DNode node, int cd) {
		if(node.left == null && node.right == null)
			return node;
		
		if(cd == Y && node.left != null)
			return findMinY(node.left, (cd+1)%DIM);
		else {
			if(node.left == null) return minY(node, findMinY(node.right, (cd+1)%DIM));
			else if(node.right == null) return minY(node, findMinY(node.left, (cd+1)%DIM));			
			else return minY(findMinY(node.left, (cd+1)%DIM), findMinY(node.right, (cd+1)%DIM));
		}
	}
	
	private E2DNode minX(E2DNode node1, E2DNode node2) {
		if(node1.getX() < node2.getX())
			return node1;
		else
			return node2;
	}
	
	private E2DNode minY(E2DNode node1, E2DNode node2) {
		if(node1.getY() < node2.getY())
			return node1;
		else
			return node2;
	}

	private boolean inBounds(Position pos) {
		return pos.getX() >= 0 && pos.getX() < w && pos.getY() >= 0 && pos.getY() < h;
	}

	@Override
	public String toString() {
		return writer.writeTree(root);
	}

	private static final int X = 0;
	private static final int Y = 1;
	private static final int DIM = 2;
}
