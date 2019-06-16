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
	private int X, Y;

	public lvl1part1(int i, int j, Jeu jeu, PlayScreen screen, World monde){
		this.jeu = jeu;
		this.monde = monde;
		this.screen = screen;
		this.X = i*jeu.V_width;
		this.Y = -j*jeu.V_height;
		decors = new ArrayList<Decors>();
		
		placementDecors(X, Y);
		placementEnnemis(X, Y);
	}

	public void placementEnnemis(int X, int Y) {
		
	}

	public void placementDecors(int X, int Y) {
		decors.add(new Sol(jeu, screen, monde, jeu.V_width/2 + X, jeu.V_height/4 + Y));
		decors.add(new Sol(jeu, screen, monde, jeu.V_width/2 + X, 3*jeu.V_height/4 + Y));
		decors.add(new Sol(jeu, screen, monde, 60+jeu.V_width/2 + X, 10+jeu.V_height/4 + Y));
	}

	public void render(SpriteBatch sb) {
		for(int i=0; i<decors.size(); i++){
			decors.get(i).render(sb);
		}
	}
}
