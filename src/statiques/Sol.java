package statiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public class Sol extends Decors{

	public Sol(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY) {
		super(screen, monde, PosX, PosY);
		
		TailleX = 20;
		TailleY = 20;
		
		this.jeu = jeu;
		
		text = new TextureRegion[1];
		text[0] = new TextureRegion();
		
		init();
		setAnimation();
	}
	
	public void setAnimation() {
			text[0].setTexture(jeu.assets.get("Assets/Carre.png"));
			animation = new Animations(text, 10);
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

}
