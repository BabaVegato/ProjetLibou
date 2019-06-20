package dynamiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public class Jumper extends Ennemi{
	
	private Texture jumper = jeu.assets.get("Assets/jumperTab.png");
	private TextureRegion[] trJumper = new TextureRegion[6];
	
	private float time = 0;

	public Jumper(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom) {
		super(jeu, screen, monde, PosX, PosY, nom);
		
		Vie = 3;
		VitX = 100000;
		VitY = 200000;
		
		setAnimation();
	}

	public void setAnimation() {
		if(state=='i') {
			tr = TextureRegion.split(jumper, 8, 19);
			for(int i=0; i<6; i++){
				trJumper[i] = tr[0][i];
			}
		animation = new Animations(trJumper, false, 15);
		}
	}
	public void render(SpriteBatch sb) {
		time++;
		if(state == 'i') {
		sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY, TailleX*2, TailleY*2);
		}
		animation.update(1);
		if(animation.isFini()){
			state = 'i';
			setAnimation();
		}
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
