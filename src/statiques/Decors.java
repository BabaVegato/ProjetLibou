package statiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public abstract class Decors {
	protected Jeu jeu;
	protected Body body;
	protected Animations animation;
	protected FixtureDef fdef;
	protected PolygonShape pshape;
	protected BodyDef bdef;
	protected int TailleX, TailleY;
	protected World monde;
	protected PlayScreen screen;
	protected int PosX, PosY;
	protected boolean aDisparu = false;
	
	protected TextureRegion[][] TextReg; 
	protected TextureRegion[] trDisp;
	protected Texture Disp;
	
	public Decors(PlayScreen screen, World monde, int PosX, int PosY){
		this.monde = monde;
		this.screen = screen;
		this.PosX = PosX;
		this.PosY = PosY;
		
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	public abstract void render(SpriteBatch sb);
	public abstract void init();
	
	public boolean getADisparu(){
		return aDisparu;
	}
	public void setADisparu(boolean x){
		aDisparu = x;
	}
}
