package com.shocker.engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameContainer implements Runnable{
	private Thread thread;
	private boolean running = false;
	private final double UPDATE_CAP = 1.0/60.0;
	private int width = 320 ,height = 240;
	private float scale = 4f;
	private String title = "ShockerEngine v1.0";
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGame game;
	
	public GameContainer(AbstractGame g){
		game = g;
	}
	public void start() {
		window = new Window(this);
		renderer = new Renderer(this);
		thread = new Thread(this);
		input = new Input(this);
		thread.run();
	}
	public void stop() {
		
	}
	public void run() {
		running = true;
		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime()/1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		double frameTime =0;
		int frame = 0;
		int fps = 0;
		
		while(running) {
			render = false;
			firstTime = System.nanoTime()/1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			while(unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;
				//TODO: Update Game
				game.update(this, (float)(UPDATE_CAP));
				input.update();
				
				if(frameTime>=1.0) {
					frameTime=0;
					fps = frame;
					frame = 0;
					System.out.println("fps: " + fps);
				}
			}
			if(render){
				//TODO: Render game
				
				renderer.clear();
				game.render(this, renderer);
				window.update();
				frame++;
				
			}
			else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		dispose();
	}

	private void dispose() {
		
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Window getWindow() {
		return window;
	}
	public Input getInput() {
		return input;
	}
}
