package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import states.PlayScreen;
import statiques.Decors;
import statiques.Pic;
import statiques.Sol;

public abstract class Partie {
	
	protected ArrayList<Sol> sols;
	private ArrayList<Pic> pics;
	private ArrayList<Ennemi> ennemis;
	protected Jeu jeu;
	protected World monde;
	protected PlayScreen screen;
	protected int X, Y;
	protected int posx, posy;
	protected int nbEnnemi;
	protected int nbPic;
	protected int nbPartie;
	protected ArrayList<Integer> ListeEnnemis;
	
	public Partie(){
		setEnnemis(new ArrayList<Ennemi>());
	}
	
	public abstract void placementEnnemis(int X, int Y);
	
	public abstract void placementDecors(int X, int Y);
	
	public abstract void render(SpriteBatch sb);

	public ArrayList<Pic> getPics() {
		return pics;
	}

	public void setPics(ArrayList<Pic> pics) {
		this.pics = pics;
	}

	public ArrayList<Ennemi> getEnnemis() {
		return ennemis;
	}

	public void setEnnemis(ArrayList<Ennemi> ennemis) {
		this.ennemis = ennemis;
	}
}
