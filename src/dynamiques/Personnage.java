package dynamiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animations;

public abstract class Personnage{
	
	private Body body;
	private Animations animation;
	private Texture[] text;
	private Jeu jeu;
	public FixtureDef fdef;
	private PolygonShape pshape;
	private BodyDef bdef;
	
	public Personnage(Jeu jeu, World Monde, Texture[] text){
		
	}
}
