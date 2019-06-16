package carte;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Partie {
	
	public Partie(){
		
	}
	
	public abstract void placementEnnemis(int X, int Y);
	
	public abstract void placementDecors(int X, int Y);
	
	public abstract void render(SpriteBatch sb);
}
