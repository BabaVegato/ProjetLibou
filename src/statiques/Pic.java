package statiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public class Pic extends Decors{
	
	private TextureRegion[][] tr;
	private TextureRegion[] trPic;
	private Texture pic;

	public Pic(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY) {
		super(screen, monde, PosX, PosY);
		
		TailleX = 20;
		TailleY = 20;
		
		this.jeu = jeu;
		
		pic = jeu.assets.get("Assets/picTab.png");
		trPic = new TextureRegion[14];
		
		init();
		setAnimation();
	}
	
	public void setAnimation() {
		tr = TextureRegion.split(pic, 21, 42);
		for(int i=0; i<14; i++){
			trPic[i] = tr[0][i];
		}
		animation = new Animations(trPic, false, 5);
	}

	public void init() {
		bdef = new BodyDef();
		bdef.position.set(PosX, PosY);
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.fixedRotation = true;
		setBody(monde.createBody(bdef));
		
		//Fixture corps
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX, TailleY);
		fdef.filter.maskBits = (short) (screen.BITJOUEUR | screen.BITOBJET);
		fdef.shape = pshape;
		fdef.density = 1f;
		fdef.filter.categoryBits = screen.BITGROUND;
		body.createFixture(fdef).setUserData("Decors");
	}
	public void render(SpriteBatch sb){
		sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY, TailleX*2, TailleY*4);
	}

}
