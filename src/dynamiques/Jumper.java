package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import states.PlayScreen;

public class Jumper extends Ennemi{
	
	private float time = 0;

	public Jumper(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom) {
		super(jeu, screen, monde, PosX, PosY, nom);
		
		Vie = 3;
		VitX = 100000;
		VitY = 200000;
	}

	public void setAnimation() {
		
	}
	public void render(SpriteBatch sb) {
		time++;
	}
	public void atk() {
		
	}
	public void mort() {
		getBody().destroyFixture(getBody().getFixtureList().first());
		getBody().destroyFixture(getBody().getFixtureList().first());	
	}

	public void mov(boolean VersLaDroite){
		//float x = getBody().getLinearVelocity().x;
		//float y = getBody().getLinearVelocity().y;
		if(time>300 && (Math.abs( screen.getJoueur().body.getPosition().x - body.getPosition().x ) < 70)){
			screen.getSndJumpJumper().setVolume((long) 0.01f, 0);
			screen.getSndJumpJumper().play();
			if(VersLaDroite){
				body.applyLinearImpulse(new Vector2(VitX, VitY), body.getLocalCenter(), true);
			}
			if(!VersLaDroite){
				body.applyLinearImpulse(new Vector2(-VitX, VitY), body.getLocalCenter(), true);
			}
			time=0;
		}
		time++;
	}
}
