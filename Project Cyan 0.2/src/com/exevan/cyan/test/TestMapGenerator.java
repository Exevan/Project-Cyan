package com.exevan.cyan.test;

import com.exevan.cyan.domain.world.Tile;
import com.exevan.cyan.domain.world.map.MapGenerator;

public class TestMapGenerator {

//	private static long seed1 = 6513574654378l;
//	private static long seed2 = 4554735243753l;
	private static long seed3 = 1234567890987654321l;
	
	public static void main(String[] args) {
		Tile[][] map = MapGenerator.generateMap(seed3, 50, 50);
		MarchingSquares ms = new MarchingSquares();
		double[] levels = {0.2d, 0.4d, 0.6d, 0.8d};
		double[][] data = new double[map.length][map[0].length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				data[i][j] = map[i][j].getHeight();			
			}
		}
		//ms.mkIsos(data, levels);
		System.out.println(ms.asciiPrintContours(data, levels));
	}
}
