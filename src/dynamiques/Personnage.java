package dynamiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private int TailleX = 15;
	private int TailleY = 15;
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
		getBody().createFixture(fdef).setUserData("Joueur");
		
		//Fixture sensor pieds
		/*fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX/3, TailleY/3);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.shape = pshape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = screen.BITPLAYER;
		body.createFixture(fdef).setUserData("Joueur" + "Pieds");*/
		
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
}
