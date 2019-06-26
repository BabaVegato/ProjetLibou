package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	protected boolean Droite;
	protected int PPM;
	protected PlayScreen screen;
	protected TextureRegion[][] tr;
	protected World monde;
	
	public Personnage(PlayScreen screen, World monde, int PosX, int PosY, int TailleX, int TailleY, boolean Gentil, String nom){
		
		
		this.screen = screen;
		this.PPM = screen.getPPM();
		this.monde = monde;
		this.TailleX = TailleX;
		this.TailleY = TailleY;
		
		bdef = new BodyDef();
		bdef.position.set(PosX/PPM, PosY/PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		bdef.fixedRotation = true;
		setBody(monde.createBody(bdef));
		
		//Fixture corps
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX/PPM, TailleY/PPM);
		fdef.shape = pshape;
		fdef.density = 1f;
		if(Gentil){
			fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET | screen.BITENNEMI);
			fdef.filter.categoryBits = screen.BITJOUEUR;
		}
		if(!Gentil){
			fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITJOUEUR);
			fdef.filter.categoryBits = screen.BITENNEMI;
		}
		body.createFixture(fdef).setUserData(nom);
		
		//Fixture sensor pieds
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX/PPM, (TailleY/8)/PPM, new Vector2(0,-TailleY/PPM), 0);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.friction = 15f;
		fdef.shape = pshape;
		//fdef.isSensor = true;
		fdef.filter.categoryBits = screen.BITJOUEUR;
		body.createFixture(fdef).setUserData(nom + "Pied");
		
	}

	public abstract void render(SpriteBatch sb);
	public abstract void setAnimation();
	
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
}
