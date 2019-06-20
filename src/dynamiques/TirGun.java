package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import states.PlayScreen;

public class TirGun extends Projectile{


	private int TailleX = 12;
	private int TailleY = 3;
	private int Vit = 20000000;
	
	public TirGun(PlayScreen screen, World monde, float PosX, float PosY, boolean Droite,String nom) {
		super(screen, monde, PosX, PosY, Droite, nom);
		
		this.PPM = screen.getPPM();
		
		initFix();

	}
	public void initFix(){
		
		bdef = new BodyDef();
		bdef.position.set(PosX/PPM, PosY/PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		bdef.fixedRotation = true;
		bdef.gravityScale = 0;
		body = monde.createBody(bdef);
		
		//Fixture corps
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX/PPM, TailleY/PPM);
		fdef.shape = pshape;
		fdef.density = 1f;
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET | screen.BITENNEMI);
		fdef.filter.categoryBits = screen.BITJOUEUR;
		body.createFixture(fdef).setUserData(nom);
	}
	public void mov(){
		if(Droite){
			body.applyForceToCenter(new Vector2(Vit,  0), true);
		}
		else{
			body.applyForceToCenter(new Vector2(-Vit,  0), true);
		}
	}
	public void render(SpriteBatch sb){
		//sb.draw(animation.getFrame(), body.getPosition().x*PPM, body.getPosition().y*PPM, TailleX, TailleY);
		mov();
	}
	public void suppr(){
		for(int i=0; i<body.getFixtureList().size; i++){
			if(body.getFixtureList().get(i).getUserData().toString().contains("TirGun")){
				body.destroyFixture(body.getFixtureList().get(i));
			}
		}
	}
}
