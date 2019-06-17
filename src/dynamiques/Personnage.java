package dynamiques;

import com.badlogic.gdx.graphics.Texture;
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
	private Body body;
	protected Animations animation;
	private FixtureDef fdef;
	private PolygonShape pshape;
	protected BodyDef bdef;
	protected int TailleX = 10;
	protected int TailleY = 20;
	protected Texture text[];
	private boolean Droite;
	
	public Personnage(PlayScreen screen, World monde, int PosX, int PosY){
		
		bdef = new BodyDef();
		bdef.position.set(PosX, PosY);
		bdef.type = BodyDef.BodyType.DynamicBody;
		bdef.fixedRotation = true;
		setBody(monde.createBody(bdef));
		
		//Fixture corps
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX, TailleY);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.shape = pshape;
		fdef.density = 1f;
		fdef.filter.categoryBits = screen.BITJOUEUR;
		body.createFixture(fdef).setUserData("Joueur");
		
		//Fixture sensor pieds
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(19*TailleX/20, TailleY/8, new Vector2(0,-TailleY), 0);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.friction = 15f;
		fdef.shape = pshape;
		//fdef.isSensor = true;
		fdef.filter.categoryBits = screen.BITJOUEUR;
		body.createFixture(fdef).setUserData("JoueurPied");
		
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
