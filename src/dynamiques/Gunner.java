package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import states.PlayScreen;

public class Gunner extends Ennemi{
	
	public Gunner(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom) {
		super(jeu, screen, monde, PosX, PosY, nom);
		
		Vie = 3;
		VitX = 20;
		VitY = 0;
	}

	public void setAnimation() {
		
	}

	public void render(SpriteBatch sb) {
		
	}

	public void atk() {
		
	}

	public void mort() {
			getBody().destroyFixture(getBody().getFixtureList().first());
			getBody().destroyFixture(getBody().getFixtureList().first());	
	}
	public void mov(boolean VersLaDroite){
		float x = getBody().getLinearVelocity().x;
		float y = getBody().getLinearVelocity().y;
		if(VersLaDroite){
			body.setLinearVelocity(new Vector2(VitX, y));
		}
		if(!VersLaDroite){
			body.setLinearVelocity(new Vector2(-VitX, y));
		}
	}

}
