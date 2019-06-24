package statiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import core.Jeu;
import states.PlayScreen;

public class Sol extends Decors{

	private Texture carre;
	private int PPM;
	private String nom;

	public Sol(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom) {
		super(screen, monde, PosX, PosY);
		this.nom = nom;
		PPM = screen.getPPM();
		
		TailleX = 20;
		TailleY = 20;
		
		this.jeu = jeu;
		
		carre = jeu.assets.get("Assets/Carre.png");
		
		init();
		setAnimation();
	}
	
	public void setAnimation() {
	}

	public void init() {
		bdef = new BodyDef();
		bdef.position.set(PosX/PPM, PosY/PPM);
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.fixedRotation = true;
		setBody(monde.createBody(bdef));
		
		//Fixture corps
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX/PPM, TailleY/PPM);
		fdef.filter.maskBits = (short) (screen.BITJOUEUR | screen.BITOBJET);
		fdef.shape = pshape;
		fdef.density = 1f;
		fdef.filter.categoryBits = screen.BITGROUND;
		body.createFixture(fdef).setUserData(nom);
	}

	public void render(SpriteBatch sb) {
		sb.draw(carre, getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY, TailleX*2, TailleY*2);
	}

}
