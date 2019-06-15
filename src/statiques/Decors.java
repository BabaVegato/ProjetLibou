package statiques;

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

public class Decors {
	protected Jeu jeu;
	private Body body;
	protected Animations animation;
	private FixtureDef fdef;
	private PolygonShape pshape;
	protected BodyDef bdef;
	private int TailleX = 30;
	private int TailleY = 15;
	protected Texture text[];
	
	public Decors(PlayScreen screen, World monde, int PosX, int PosY){
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
		fdef.friction = 10f;
		fdef.density = 1f;
		fdef.filter.categoryBits = screen.BITGROUND;
		getBody().createFixture(fdef).setUserData("Decors");
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY, TailleX*2, TailleY*2);
	}
}
