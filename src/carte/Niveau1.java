package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import states.PlayScreen;
import statiques.Item;
import statiques.Pic;
import statiques.Sol;

public class Niveau1 {

	private ArrayList<Partie> parties;
	private int r;
	private Jeu jeu;
	private World monde;
	private PlayScreen screen;
	private int nbPartie = 0;
	private int vieEnnemi;
	private Ennemi ennemi;
	private Pic pic;
	private Item item;
	private Sol sol;
	

	public Niveau1(Jeu jeu, World monde, PlayScreen screen){
		
		this.jeu = jeu;
		this.monde = monde;
		this.screen = screen;
		setParties(new ArrayList<Partie>());
		
		init(1);
		
	}
	
	public void init(int NbDePartiesDifferentes){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				
				r = randInt(1, NbDePartiesDifferentes);
				
				if(r == 1){
					getParties().add(new lvl1part1(i, j, jeu, screen, monde, nbPartie));
				}
				if(r == 2){
					getParties().add(new lvl1part2(i, j, jeu, screen, monde, nbPartie));
				}
				nbPartie++;
			}
		}
	}
	public void render(SpriteBatch sb){
		for(int i=0; i<getParties().size(); i++){
			getParties().get(i).render(sb);
		}
	}
	
	public int randInt(int Min, int Max){
		//prend un int dans [Min;Max]
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
	public void GestionVie(String IDNbEnnemi, String IDNbPartie, int X){
		//Retire X de vie au bon ennemi
		
		ennemi = getParties().get(Integer.parseInt(IDNbPartie)).getEnnemis().get(Integer.parseInt(IDNbEnnemi));
		
		vieEnnemi = ennemi.getVie();
		if(vieEnnemi-X <= 0){
			ennemi.mort();
		}
		else{
			ennemi.setVie(vieEnnemi-X);
		}
		
	}

	public void GestionPic(String iDNbPic, String iDNbPartie) {
		pic = getParties().get(Integer.parseInt(iDNbPartie)).getPics().get(Integer.parseInt(iDNbPic));
		pic.setContactPic(true);
	}
	
	public void GestionItem(String iDNb, String iDNbPartie) {
		item = getParties().get(Integer.parseInt(iDNbPartie)).getItem().get(Integer.parseInt(iDNb));
		item.getBody().destroyFixture(item.getBody().getFixtureList().get(0));
	}

	public ArrayList<Partie> getParties() {
		return parties;
	}

	public void setParties(ArrayList<Partie> parties) {
		this.parties = parties;
	}

	public void DestructionDecors(String iDDecorsGenre, String iDNbPartieDecors, String iDNbDecors) {
		if(iDDecorsGenre.contains("Sol")){
			System.out.println("Destrudec");
			sol = getParties().get(Integer.parseInt(iDNbPartieDecors)).getSols().get(Integer.parseInt(iDNbDecors));
			sol.getBody().destroyFixture(sol.getBody().getFixtureList().get(0));
			sol.setADisparu(true);
		}
		if(iDDecorsGenre.contains("Pic")){
			pic = getParties().get(Integer.parseInt(iDNbPartieDecors)).getPics().get(Integer.parseInt(iDNbDecors));
			pic.getBody().destroyFixture(pic.getBody().getFixtureList().get(0));
			pic.setADisparu(true);
		}
		
	}

}
