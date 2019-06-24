package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import states.PlayScreen;
import statiques.Decors;
import statiques.Item;
import statiques.Pic;
import statiques.Sol;

public abstract class Partie {
	
	protected ArrayList<Sol> sols;
	private ArrayList<Pic> pics;
	private ArrayList<Ennemi> ennemis;
	protected ArrayList<Integer> ListeEnnemis;
	protected ArrayList<Item> items = new ArrayList<Item>();
	protected Jeu jeu;
	protected World monde;
	protected PlayScreen screen;
	protected int X, Y;
	protected int posx, posy;
	protected int nbEnnemi;
	protected int nbPic;
	protected int nbPartie;
	protected int nbItem;
	protected int nbSol;
	
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
	public ArrayList<Item> getItem() {
		return items;
	}
	public void setItem(ArrayList<Item> items) {
		this.items = items;
	}
	public ArrayList<Sol> getSols() {
		return sols;
	}
	public void setSols(ArrayList<Sol> sols) {
		this.sols = sols;
	}
}
