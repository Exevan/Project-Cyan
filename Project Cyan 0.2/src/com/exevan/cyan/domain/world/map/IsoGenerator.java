package com.exevan.cyan.domain.world.map;

import java.awt.geom.GeneralPath;

public class IsoGenerator {

	public GeneralPath[] generateContours(float[][] map, float[] levels) {
		GeneralPath[] isos = new GeneralPath[levels.length];
		float[][] expandedMap = expandMap(map);
		for (int i = 0; i < levels.length; i++) {
			IsoCell[][] contour = generateContour(expandedMap, levels[i]);
			GeneralPath iso = generateIso(contour, expandedMap, levels[i]);
			isos[i] = iso;
		}
		return isos;
	}

	private float[][] expandMap(float[][] map) {
		int w = map.length;
		int h = map[0].length;
		float[][] expandedMap = new float[map.length + 2][map[0].length + 2];
		for(int i = 0; i < w+2; i++) {
			expandedMap[i][0] = Float.MIN_VALUE;
			expandedMap[i][h+1] = Float.MIN_VALUE;
		}

		for (int j = 0; j < h+2; j++) {
			expandedMap[0][j] = Float.MIN_VALUE;
			expandedMap[w+1][j] = Float.MIN_VALUE;			
		}

		for (int i = 1; i < w+1; i++) {
			for (int j = h; j < h+1; j++) {
				expandedMap[i][j] = map[i-1][j-1];
			}
		}

		return expandedMap;
	}

	private IsoCell[][] generateContour(float[][] map, float level) {
		IsoCell[][] contour = new IsoCell[map.length-1][map[0].length-1];
		int w = map.length;
		int h = map[0].length;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int ll = (map[i][j] > level) ? 1 : 0;
				int lr = (map[i][j+1] > level) ? 2 : 0;
				int ur = (map[i+1][j+1] > level) ? 4 : 0;
				int ul = (map[i+1][j] > level) ? 8 : 0;		
				int index = ul | ur | lr | ll;	
				boolean flipped = false;

				float avg = (map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j]) / 4;
				if((index == 5 || index == 10) && avg < level)
					flipped = true;

				contour[i][j] = new IsoCell();
				contour[i][j].setIndex(index);
				contour[i][j].setFlipped(flipped);
			}
		}	

		return contour;
	}

	private GeneralPath generateIso(IsoCell[][] contours, float[][] map, float level) {
		GeneralPath iso = new GeneralPath();
		int w = map.length;
		int h = map[0].length;

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				//TODO: Interpolate line crossing points for each IsoCell.
			}
		}

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if(contours[i][j].getIndex() != 0
						&& contours[i][j].getIndex() != 5
						&& contours[i][j].getIndex() != 10
						&& contours[i][j].getIndex() != 15)
					buildSubIso(iso, contours, i, j);
					
			}
		}

		return iso;
	}

	private void buildSubIso(GeneralPath iso, IsoCell[][] contours, int x, int y) {
		
	}


}