package com.exevan.cyan.domain.world.map;

import java.util.Random;

import com.exevan.cyan.domain.world.Tile;

public class MapGenerator {
	
	private static Random rand = new Random();
	private static int w, h;

	public static Tile[][] generateMap(long seed, int width, int height) {
		Tile[][] map = new Tile[width][height];
		float[][] hMap = generateHeightMap(seed, width, height);
		for (int i = 0; i < hMap.length; i++) {
			for (int j = 0; j < hMap[0].length; j++) {
				map[i][j] = new Tile(hMap[i][j]);
			}
		}
		return map;
	}
	
	private static float[][] generateHeightMap(long seed, int width, int height) {
		
		//Initialize map and grid
		w = width; h = height; rand.setSeed(seed);
		int oct = 8;  
		float[][] map = new float[width][height];	
		float[][][][] grid = new float[oct][][][];
	
		//Calculate fBm parameters
		float freq = 2.0f / (width+height);
		freq = (freq < 4) ? 4 : freq;
		float lac = 2.0f;
		float per = 0.5f;
		float amp = 1.0f;

		//Generate gradients
		for(int i = oct - 1; i >= 0; i--) {
			int w = (int) (freq);
			int h = (int) (freq);
			grid[i] = generateGrad(w, h, amp);
			System.out.println(w + "\t" + h + "\t" + amp);
			freq *= lac;
			amp *= per;
		}

		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;

		//Calculate fBm
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				float p = 0.0f;
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

	private static float perlinNoise(float u, float v, float[][][] grad) {
		
		//Rescale map points to grid points
		float x = (u*(grad.length-1))/(w); 
		float y = (v*(grad[0].length-1))/(h);
		
		//Determine nearest grid points //TODO: fix this properly
		int x0 = (int) x;
		int y0 = (int) y;
		int x1 = x0 + 1;
		int y1 = y0 + 1;
//		int x1 = (x0 + 1) % grad.length;
//		int y1 = (y0 + 1) % grad[0].length;

		//Determine weights using S-function
		float fx = x - (int)x;
		float fy = y - (int)y;
		float wx = fx*fx*(3-2*fx);
		float wy = fy*fy*(3-2*fy);

		//Calculate noise value
		float l0, l1, ix, iy, p;
		l0 = dotGrad(x0, y0, x, y, grad);
		l1 = dotGrad(x1, y0, x, y, grad);
		ix = cerp(l0, l1, wx);
		l0 = dotGrad(x0, y1, x, y, grad);
		l1 = dotGrad(x1, y1, x, y, grad);
		iy = cerp(l0, l1, wx);
		p = cerp(ix, iy, wy);

		return p;
	}

	@SuppressWarnings("unused")
	private static float lerp(float a, float b, float w) {
		return (1.0f - w)*a + w*b;
	}

	private static float cerp(float a, float b, float w) {
		float ft = w * 3.1415927f;
		float f = (float) ((1 - Math.cos(ft)) * 0.5f);
		return  a*(1-f) + b*f;
	}

	private static float dotGrad(int i, int j, float x, float y, float[][][] grad) {
		float dx = i - x;
		float dy = j - y;
		return (dx*grad[i][j][0] + dy*grad[i][j][1]);

	}

	private static float[][][] generateGrad(int width, int height, float amp) {
		float[][][] grad = new float[width][height][2];
		for (int i = 0; i < grad.length; i++) {
			for (int j = 0; j < grad[0].length; j++) {
				grad[i][j][0] = (rand.nextFloat() * 2.0f - 1.0f) * amp;
				grad[i][j][1] = (rand.nextFloat() * 2.0f - 1.0f) * amp;
			}
		}
		return grad;
	}

}
