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
	private int PPM;
	private boolean contactPic;
	private String nom;

	public Pic(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom) {
		super(screen, monde, PosX, PosY);
		
		this.nom = nom;
		TailleX = 20;
		TailleY = 20;
		this.screen = screen;
		this.monde = monde;
		this.jeu = jeu;
		PPM = screen.getPPM();
		
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
		animation = new Animations(trPic, true, 10);
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
		if(contactPic){
			animation.update(1f);
		}
		if(animation.isFini()){
			contactPic = false;
			animation.setFini(false);
		}
	}

	public boolean isContactPic() {
		return contactPic;
	}
	public void setContactPic(boolean contactPic) {
		this.contactPic = contactPic;
	}
}
