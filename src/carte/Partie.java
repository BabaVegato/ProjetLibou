package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import states.PlayScreen;
import statiques.Decors;

public abstract class Partie {
	
	protected ArrayList<Decors> decors;
	protected ArrayList<Ennemi> ennemis;
	protected Jeu jeu;
	protected World monde;
	protected PlayScreen screen;
	protected int X, Y;
	protected int posx, posy;
	protected int nbEnnemi;
	protected int nbPartie;
	protected ArrayList<Integer> ListeEnnemis;
	
	public Partie(){
		ennemis = new ArrayList<Ennemi>();
	}
	
	public abstract void placementEnnemis(int X, int Y);
	
	public abstract void placementDecors(int X, int Y);
	
	public abstract void render(SpriteBatch sb);
}
