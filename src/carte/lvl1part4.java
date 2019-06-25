package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import dynamiques.Gunner;
import dynamiques.Jumper;
import states.PlayScreen;
import statiques.BonusVie;
import statiques.Pic;
import statiques.Sol;

public class lvl1part4 extends Partie{

	private char[][] design = {
			{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'},
			{'0', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'0', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'0', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'}, 
			{'s', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', 'e', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', 's', 's', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', '0', '0', 'e', 'j', 's', '0', '0', '0', 's', '0', '0', '0', '0', 's'},
			{'s', 's', 's', 's', 's', 's', '0', '0', '0', 's', 's', 's', 's', 's', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'s', '0', '0', 'e', 'j', '0', 'e', '0', '0', 'e', 'e', 'e', 'e', '0', 's'},
			{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'}};
	private int Difficulte = 2;
	private String Gauche = "h";
	private String Droite = "b";

	public lvl1part4(int i, int j, Jeu jeu, PlayScreen screen, World monde, int nbPartie){
		nbEnnemi = 0;
		nbPic = 0;
		nbSol = 0;
		nbItem = 0;
		this.nbPartie = nbPartie;
		ListeEnnemis = new ArrayList<Integer>();
		this.jeu = jeu;
		this.monde = monde;
		this.screen = screen;

		this.X = i*screen.getTailleBloc()*2*screen.getBlocsParPartieX();
		this.Y = -j*screen.getTailleBloc()*2*screen.getBlocsParPartieY();
		sols = new ArrayList<Sol>();
		setPics(new ArrayList<Pic>());
		setEnnemis(new ArrayList<Ennemi>());
		
		placementDecors(X, Y);
		placementEnnemis(X, Y);
		placementItems(X, Y);
		
		
	}

	public void placementEnnemis(int X, int Y) {
		for(int i=0; i<screen.getBlocsParPartieX(); i++){
			for(int j=0; j<screen.getBlocsParPartieY(); j++){
				
				posx = X + i*screen.getTailleBloc()*2;
				posy = jeu.V_height + Y - j*screen.getTailleBloc()*2;
				
				if(design[j][i] == 'g'){
					getEnnemis().add(new Gunner(jeu, screen, monde, posx, posy, "Ennemi:" + nbEnnemi + ":" + nbPartie + ":Gunner"));
					nbEnnemi++;
				}
				if(design[j][i] == 'j'){
					getEnnemis().add(new Jumper(jeu, screen, monde, posx, posy, "Ennemi:" + nbEnnemi + ":" + nbPartie + ":Jumper"));
					nbEnnemi++;
				}
			}
		}
	}
	public void placementItems(int X, int Y){
		for(int i=0; i<screen.getBlocsParPartieX(); i++){
			for(int j=0; j<screen.getBlocsParPartieY(); j++){
				
				posy = X + i*screen.getTailleBloc()*2;
				posx = jeu.V_height + Y - j*screen.getTailleBloc()*2;
				
				if(design[j][i] == 'B'){
					getItem().add(new BonusVie(jeu, screen, monde, "Item:" + nbItem + ":" + nbPartie + ":BonusVie", posy, posx));
					nbItem++;
				}
			}
		}
	}

	public void placementDecors(int X, int Y) {
		for(int i=0; i<screen.getBlocsParPartieX(); i++){
			for(int j=0; j<screen.getBlocsParPartieY(); j++){
				
				posx = X + i*screen.getTailleBloc()*2;
				posy = jeu.V_height + Y - j*screen.getTailleBloc()*2;
				
				if(design[j][i] == 's'){
					sols.add(new Sol(jeu, screen, monde, posx, posy, "Sol:" + nbSol + ":" + nbPartie + ":Decors"));
					nbSol++;
				}
				if(design[j][i] == 'p'){
					getPics().add(new Pic(jeu, screen, monde, posx, posy, "Pic:" + nbPic + ":" + nbPartie + ":Decors"));
					nbPic++;
				}
			}
		}
	}

	public void render(SpriteBatch sb) {
		if(!sols.isEmpty()){
			for(int i=0; i<sols.size(); i++){
				if(!sols.get(i).getADisparu()) sols.get(i).render(sb);
			}
		}
		if(!getPics().isEmpty()){
			for(int i=0; i<getPics().size(); i++){
				if(!getPics().get(i).getADisparu()) getPics().get(i).render(sb);
			}
		}
		if(!getEnnemis().isEmpty()){
			for(int i=0; i<getEnnemis().size(); i++){
				if(!getEnnemis().get(i).isaDisparu()) getEnnemis().get(i).render(sb);
			}
		}
		if(!getItem().isEmpty()){
			for(int i=0; i<getItem().size(); i++){
				getItem().get(i).render(sb);
			}
		}
	}
}
