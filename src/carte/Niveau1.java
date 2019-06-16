package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import states.PlayScreen;

public class Niveau1 {

	private ArrayList<Partie> parties;
	private int r;
	private Jeu jeu;
	private World monde;
	private PlayScreen screen;

	public Niveau1(Jeu jeu, World monde, PlayScreen screen){
		
		this.jeu = jeu;
		this.monde = monde;
		this.screen = screen;
		parties = new ArrayList<Partie>();
		
		init(1);
		
	}
	
	public void init(int NbDePartiesDifferentes){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				
				r = randInt(1, NbDePartiesDifferentes);
				
				if(r == 1){
					parties.add(new lvl1part1(i, j, jeu, screen, monde));
				}
				if(r == 2){
					//parties.add(new lvl1part2(i, j, jeu, screen, monde));
				}
			}
		}
	}
	public void render(SpriteBatch sb){
		for(int i=0; i<parties.size(); i++){
			parties.get(i).render(sb);
		}
	}
	
	public int randInt(int Min, int Max){
		//prend un int dans [Min;Max]
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
}
