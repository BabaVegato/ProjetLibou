package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import states.PlayScreen;

public class Niveau1 {

	private ArrayList<Partie> parties;
	private int r;
	private Jeu jeu;
	private World monde;
	private PlayScreen screen;
	private int nbPartie = 0;
	private int vieEnnemi;
	private Ennemi ennemi;
	

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
					parties.add(new lvl1part1(i, j, jeu, screen, monde, nbPartie));
				}
				if(r == 2){
					parties.add(new lvl1part2(i, j, jeu, screen, monde, nbPartie));
				}
				nbPartie++;
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
	public void GestionVie(String IDNbEnnemi, String IDNbPartie, int X){
		//Retire X de vie au bon ennemi
		
		ennemi = parties.get(Integer.parseInt(IDNbPartie)).ennemis.get(Integer.parseInt(IDNbEnnemi));
		
		vieEnnemi = ennemi.getVie();
		if(vieEnnemi-X <= 0){
			ennemi.getBody().destroyFixture(ennemi.getBody().getFixtureList().first());
			ennemi.getBody().destroyFixture(ennemi.getBody().getFixtureList().first());
			
		}
		else{
			ennemi.setVie(vieEnnemi-X);
		}
		
	}
}
