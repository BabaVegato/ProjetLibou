package carte;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Partie {
	
	public Partie(){
		
	}
	
	public abstract void placementEnnemis();
	
	public abstract void placementDecors();
	
	public abstract void render(SpriteBatch sb);
}
