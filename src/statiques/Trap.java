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

public class Trap extends Decors{
	
	private TextureRegion[][] tr;
	private TextureRegion[] trTrap;
	private Texture trap;
	private int PPM;
	private boolean passeDevant;
	private String nom;

	public Trap(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom) {
		super(screen, monde, PosX, PosY);
		
		this.nom = nom;
		TailleX = 20;
		TailleY = 20;
		this.screen = screen;
		this.monde = monde;
		this.jeu = jeu;
		PPM = screen.getPPM();
		
		trap = jeu.assets.get("Assets/picTab.png");
		trTrap = new TextureRegion[14];
		
		init();
		setAnimation();
	}
	
	public void setAnimation() {
		tr = TextureRegion.split(trap, 21, 42);
		for(int i=0; i<14; i++){
			trTrap[i] = tr[0][i];
		}
		animation = new Animations(trTrap, true, 10);
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
	public void render(SpriteBatch sb){
		sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY, TailleX*2, TailleY*4);
		if(passeDevant){
			animation.update(1f);
		}
	}

	public boolean isPasseDevant() {
		return passeDevant;
	}
	public void setPasseDevant(boolean passeDevant) {
		this.passeDevant = passeDevant;
	}
}
