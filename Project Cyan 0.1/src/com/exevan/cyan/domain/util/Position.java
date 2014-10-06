package com.exevan.cyan.domain.util;

public class Position implements Comparable<Position>{

	private int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public boolean checkX(int x) {
		return (Integer.MIN_VALUE < x) && (x < Integer.MAX_VALUE);

	}

	public void setX(int x) {
		if(checkX(x))
			this.x = x;
	}

	public int getY() {
		return y;
	}

	public boolean checkY(int y) {
		return (Integer.MIN_VALUE < y) && (y < Integer.MAX_VALUE);

	}

	public void setY(int y) {
		if(checkY(y))
			this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(! o.getClass().equals(this.getClass()))
			return false;
		Position other = (Position) o;
		return (this.getX() == other.getX()) && (this.getY() == other.getY());

	}

	@Override
	public int hashCode() {
		return Integer.parseInt("" + getX() + getX());
	}

	@Override
	public int compareTo(Position other) {
		return (int) (Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY()) -
				Math.sqrt(other.getX()*other.getX() + other.getY()*other.getY()));
	}
}
