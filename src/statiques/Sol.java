package statiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import handlers.Animations;
import states.PlayScreen;

public class Sol extends Decors{

	public Sol(PlayScreen screen, World monde, int PosX, int PosY) {
		super(screen, monde, PosX, PosY);
		
		this.jeu = jeu;
		
		
		text = new Texture[4];
		
		setAnimation();
	}
	
	public void setAnimation() {
			text[0] = jeu.assets.get("Assets/idle1.png");
			animation = new Animations(text, 10);
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), getBody().getPosition().x-14, getBody().getPosition().y-15, 30, 30);
		animation.update(1);
	}
}

	}
	
}
