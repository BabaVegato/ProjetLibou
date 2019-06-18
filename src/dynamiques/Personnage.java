package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public abstract class Personnage{
	
	protected Jeu jeu;
	protected Body body;
	protected Animations animation;
	protected FixtureDef fdef;
	protected PolygonShape pshape;
	protected BodyDef bdef;
	protected int TailleX = 10;
	protected int TailleY = 20;
	private boolean Droite;
	private int PPM;
	private PlayScreen screen;
	
	public Personnage(PlayScreen screen, World monde, int PosX, int PosY, String nom){
		
		this.screen = screen;
		this.PPM = screen.getPPM();
		
		bdef = new BodyDef();
		bdef.position.set(PosX/PPM, PosY/PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		bdef.fixedRotation = true;
		setBody(monde.createBody(bdef));
		
		//Fixture corps
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX/PPM, TailleY/PPM);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.shape = pshape;
		fdef.density = 1f;
		fdef.filter.categoryBits = screen.BITJOUEUR;
		body.createFixture(fdef).setUserData(nom);
		
		//Fixture sensor pieds
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox((19*TailleX/20)/PPM, (TailleY/8)/PPM, new Vector2(0,-TailleY/PPM), 0);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.friction = 15f;
		fdef.shape = pshape;
		//fdef.isSensor = true;
		fdef.filter.categoryBits = screen.BITJOUEUR;
		body.createFixture(fdef).setUserData(nom + "Pied");
		
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public boolean isDroite() {
		return Droite;
	}

	public void setDroite(boolean droite) {
		Droite = droite;
	}
	public abstract void render(SpriteBatch sb);
	public abstract void setAnimation();
}
