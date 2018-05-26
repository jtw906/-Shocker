package shocker;

import java.awt.event.KeyEvent;

import com.shocker.engine.AbstractGame;
import com.shocker.engine.GameContainer;
import com.shocker.engine.Renderer;
import com.shocker.engine.gfx.Image;

public class GameManager extends AbstractGame {
	private Image image;
	public GameManager() {
		image = new Image("/test.png");
	}
	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_A)){
			System.out.println("A was pressed");
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(image, gc.getInput().getMouseX(), gc.getInput().getMouseY());	
		
	}
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
