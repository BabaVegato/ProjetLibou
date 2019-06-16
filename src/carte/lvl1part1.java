package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import states.PlayScreen;
import statiques.Decors;
import statiques.Sol;

public class lvl1part1 extends Partie{

	private ArrayList<Decors> decors;
	private ArrayList<Ennemi> ennemis;
	private Jeu jeu;
	private World monde;
	private PlayScreen screen;

	public lvl1part1(int X, int Y, Jeu jeu, PlayScreen screen, World monde){
		this.jeu = jeu;
		this.monde = monde;
		this.screen = screen;
		decors = new ArrayList<Decors>();
		
		placementDecors();
		placementEnnemis();
	}

	public void placementEnnemis() {
		
	}

	public void placementDecors() {
		decors.add(new Sol(jeu, screen, monde, jeu.V_width/2, jeu.V_height/4));
	}

	public void render(SpriteBatch sb) {
		for(int i=0; i<decors.size(); i++){
			decors.get(i).render(sb);
		}
	}
}
