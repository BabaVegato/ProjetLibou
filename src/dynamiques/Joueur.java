package dynamiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import core.Jeu;
import handlers.Animations;
import states.PlayScreen;


public class Joueur extends Personnage{
	
	public Joueur(Jeu jeu, PlayScreen screen, World Monde, int PosX, int PosY){
		super(screen, Monde, PosX, PosY);
		
		this.jeu = jeu;
		
		Droite = true;
		
		text = new Texture[4];
		
		setAnimation();
	}
	
	public void setAnimation() {
		if(Droite) {
			text[0] = jeu.assets.get("Assets/idle1.png");
			text[1] = jeu.assets.get("Assets/idle2.png");
			text[2] = jeu.assets.get("Assets/idle3.png");
			text[3] = jeu.assets.get("Assets/idle4.png");
			animation = new Animations(text, 10);
		}
		else{
			text[0] = jeu.assets.get("Assets/idle1.png");
			text[1] = jeu.assets.get("Assets/idle2.png");
			text[2] = jeu.assets.get("Assets/idle3.png");
			text[3] = jeu.assets.get("Assets/idle4.png");
			animation = new Animations(text, 10);
		}
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), body.getPosition().x-14, body.getPosition().y-15, 30, 30);
		animation.update(1);
	}
}
