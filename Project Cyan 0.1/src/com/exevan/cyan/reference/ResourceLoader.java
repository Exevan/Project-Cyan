package com.exevan.cyan.reference;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	public static void loadResources() {
		loadSprites();
	}
	
	private static void loadSprites() {
		try {
			BufferedImage player = ImageIO.read(new File(".///res///player.png"));
			Reference.player = player;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
