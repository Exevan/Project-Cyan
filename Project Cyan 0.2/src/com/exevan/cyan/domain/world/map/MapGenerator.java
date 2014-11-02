package com.exevan.cyan.domain.world.map;

import java.util.Random;

public class MapGenerator {

	private static int GRID_SCALE = 50;
	private static Random rand = new Random();
	private static int w, h;

	@Deprecated
	public static double[][] generateMap_OLD(long seed, int width, int height) {
		double[][] map = new double[width][height];
		double[][][] grad = generateGrad((int) (width/GRID_SCALE)+1, (int) (height/GRID_SCALE)+1, 1.0);

		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				double p = perlinNoise(((double) i) / 100, ((double) j) / 100, grad);
				map[i][j] = p;
				max = (max < p) ? p : max;
				min = (min > p) ? p : min;
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = (map[i][j] - min) / (max - min); //Limit 0 to 1
			}
		}

		return map;
	}

	public static double[][] generateMap(long seed, int width, int height) {
		
		//Initialize map and grid
		w = width; h = height; rand.setSeed(seed);
		int oct = 8;
		double[][] map = new double[width][height];	
		double[][][][] grid = new double[oct][][][];
	
		//Calculate fBm parameters
		double freq = 2.0d / (width+height);
		freq = (freq < 4) ? 4 : freq;
		double lac = 2.0d;
		double per = 0.5d;
		double amp = 1.0d;

		//Generate gradients
		for(int i = oct - 1; i >= 0; i--) {
			int w = (int) (freq);
			int h = (int) (freq);
			grid[i] = generateGrad(w, h, amp);
			System.out.println(w + "\t" + h + "\t" + amp);
			freq *= lac;
			amp *= per;
		}

		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;

		//Calculate fBm
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				double p = 0.0;
				for(int o = 0; o < oct; o++) {
					p += perlinNoise(i, j, grid[o]);
				}
				map[i][j] = p;
				max = (max < p) ? p : max;
				min = (min > p) ? p : min;
			}
		}

		//Rescale fBm between 0.0 and 1.0
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = (map[i][j] - min) / (max - min);
			}
		}

		return map;
	}

	private static double perlinNoise(double u, double v, double[][][] grad) {
		
		//Rescale map points to grid points
		double x = (u*(grad.length-1))/(w); 
		double y = (v*(grad[0].length-1))/(h);
		
		//Determine nearest grid points //TODO: fix this properly
		int x0 = (int) x;
		int y0 = (int) y;
		int x1 = x0 + 1;
		int y1 = y0 + 1;
//		int x1 = (x0 + 1) % grad.length;
//		int y1 = (y0 + 1) % grad[0].length;

		//Determine weights using S-function
		double fx = x - (int)x;
		double fy = y - (int)y;
		double wx = fx*fx*(3-2*fx);
		double wy = fy*fy*(3-2*fy);

		//Calculate noise value
		double l0, l1, ix, iy, p;
		l0 = dotGrid(x0, y0, x, y, grad);
		l1 = dotGrid(x1, y0, x, y, grad);
		ix = cerp(l0, l1, wx);
		l0 = dotGrid(x0, y1, x, y, grad);
		l1 = dotGrid(x1, y1, x, y, grad);
		iy = cerp(l0, l1, wx);
		p = cerp(ix, iy, wy);

		return p;
	}

	@SuppressWarnings("unused")
	private static double lerp(double a, double b, double w) {
		return (1.0f - w)*a + w*b;
	}

	private static double cerp(double a, double b, double w) {
		double ft = w * 3.1415927;
		double f = (1 - Math.cos(ft)) * 0.5;
		return  a*(1-f) + b*f;
	}

	private static double dotGrid(int i, int j, double x, double y, double[][][] grad) {
		double dx = i - x;
		double dy = j - y;
		return (dx*grad[i][j][0] + dy*grad[i][j][1]);

	}

	private static double[][][] generateGrad(int width, int height, double amp) {
		double[][][] grad = new double[width][height][2];
		for (int i = 0; i < grad.length; i++) {
			for (int j = 0; j < grad[0].length; j++) {
				grad[i][j][0] = (rand.nextFloat() * 2.0 - 1.0) * amp;
				grad[i][j][1] = (rand.nextFloat() * 2.0 - 1.0) * amp;
			}
		}
		return grad;
	}

}
