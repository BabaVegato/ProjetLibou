package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;
import states.PlayScreen;

public abstract class Projectile {

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
	protected float PosX;
	protected float PosY;
	protected World monde;
	protected String nom;
	
	public Projectile(PlayScreen screen, World monde, float PosX, float PosY, boolean Droite,String nom) {
		this.PosX = PosX;
		this.PosY = PosY;
		this.monde = monde;
		this.nom = nom;
		this.screen = screen;
		this.Droite = Droite;
	}
	public abstract void initFix();
	public abstract void render(SpriteBatch sb);
	public abstract void mov();
	public abstract void suppr();
}
