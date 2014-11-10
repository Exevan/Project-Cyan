package com.exevan.cyan.domain.world.map;

public class IsoCell {
	
	private int index;
	private float top, bottom, left, right;
	private boolean flipped;
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public float getTop() {
		return top;
	}
	
	public void setTop(float top) {
		this.top = top;
	}
	
	public float getBottom() {
		return bottom;
	}
	
	public void setBottom(float bottom) {
		this.bottom = bottom;
	}
	
	public float getLeft() {
		return left;
	}
	
	public void setLeft(float left) {
		this.left = left;
	}
	
	public float getRight() {
		return right;
	}
	
	public void setRight(float right) {
		this.right = right;
	}
	
	public boolean isFlipped() {
		return flipped;
	}
	
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	
}
