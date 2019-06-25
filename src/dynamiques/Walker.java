package dynamiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public class Walker extends Ennemi {

	private Texture walk = jeu.assets.get("Assets/spinnerTab.png");
	private TextureRegion[] trWalk = new TextureRegion[3];
	
	private float time = 0;
	
	public Walker(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom) {
		super(jeu, screen, monde, PosX, PosY, nom);
		
		Vie = 3;
		VitX = 5;
		VitY = 0;
		
		setAnimation();
	}

	public void setAnimation() {
			tr = TextureRegion.split(walk, 20, 20);
			for(int i=0; i<3; i++){
				trWalk[i] = tr[0][i];
			}
		animation = new Animations(trWalk, false, 15);
	}

	public void render(SpriteBatch sb) {
		time++;
	
		sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY, TailleX*2, TailleY*2);
		animation.update(1);
		
		if(animation.isFini()){
			setAnimation();
		}
	}

	public void atk() {
		
	}

	public void mort() {
			getBody().destroyFixture(getBody().getFixtureList().first());
			getBody().destroyFixture(getBody().getFixtureList().first());
			aDisparu = true;
	}
	public void mov(boolean VersLaDroite){
		//float x = getBody().getLinearVelocity().x;
		float y = getBody().getLinearVelocity().y;
		if(VersLaDroite){
			body.setLinearVelocity(new Vector2(VitX, y));
		}
		if(!VersLaDroite){
			body.setLinearVelocity(new Vector2(-VitX, y));
		}
	}

}
