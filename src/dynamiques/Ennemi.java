package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import core.Jeu;
import states.PlayScreen;


public abstract class Ennemi extends Personnage{
	
	protected char state = 'i';
	protected PlayScreen screen;
	protected int Vie;
	protected float VitX;
	protected float VitY;
	protected boolean aDisparu = false;
	
	public Ennemi(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, int TailleX, int TailleY, String nom){
		super(screen, monde, PosX, PosY, TailleX, TailleY, false, nom);
		
		this.jeu = jeu;
		this.screen = screen;
		setDroite(true);
	}
	
	public abstract void setAnimation();
	
	public abstract void render(SpriteBatch sb);

	public abstract void atk();
	
	public abstract void mort();

	public abstract void mov(boolean VersLaDroite);
	
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	public int getVie() {
		return Vie;
	}
	public void setVie(int vie) {
		Vie = vie;
	}

	public boolean isaDisparu() {
		return aDisparu;
	}

	public void setaDisparu(boolean aDisparu) {
		this.aDisparu = aDisparu;
	}

}
