package com.shocker.engine;
import java.awt.image.DataBufferInt;

import com.shocker.engine.gfx.Image;
public class Renderer {
	private int pW, pH;
	private int[] p;
	public Renderer(GameContainer gc) {
		pW = gc.getWidth();
		pH = gc.getHeight();
		p = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		}
	public void clear() {
		for(int i = 0; i<p.length;i++) {
			p[i]=0;
		}
	}
	public void setPixel(int x, int y, int value) {
		if((x<0||x>=pW||y<0||y>=pH)||value == 0xffff00ff) {
			return;
		}
		p[x+y * pW] = value;
	}
	public void drawImage(Image image,int offx, int offy) {
		for(int y = 0; y< image.getHeight();y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				setPixel(x + offx,y+offy,image.getPixels()[x+y*image.getWidth()]);
			}
		}
		
	}
}
