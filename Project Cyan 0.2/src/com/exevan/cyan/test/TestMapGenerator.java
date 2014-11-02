package com.exevan.cyan.test;

import com.exevan.cyan.domain.world.map.MapGenerator;

public class TestMapGenerator {

//	private static long seed1 = 6513574654378l;
//	private static long seed2 = 4554735243753l;
	private static long seed3 = 1234567890987654321l;
	
	public static void main(String[] args) {
		double[][] map = MapGenerator.generateMap(seed3, 200, 120);
		for (double[] ds : map) {
			for (double d : ds) {
				System.out.print(d + "\t");
			}
			System.out.println("");
		}
	}
}
