package statiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public class Sol extends Decors{

	public Sol(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY) {
		super(screen, monde, PosX, PosY);
		
		this.jeu = jeu;
		
		
		text = new Texture[1];
		
		setAnimation();
	}
	
	public void setAnimation() {
			text[0] = jeu.assets.get("Assets/sol.png");
			animation = new Animations(text, 10);
	}
}
