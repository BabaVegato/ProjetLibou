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

public class lvl1part1 extends Partie{

	private char[][] design = {
			{'s', 's', 's', 's', 's', 's', 's', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', 's', '0', '0', '0', '0', '0', 'e', 'e', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', 'p', 'p', 'p', 'p', 'p', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'}, 
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', 'e', 'e', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', 'p', 'p', 's', 'p', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', 's', 's', 's', 's', 's', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', 's', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', '0', '0', '0', '0', 'e', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
			{'s', 's', 's', 's', 's', 's', '0', '0', 's', 's', 's', 's', 's', 's', 's'}};

	public lvl1part1(int i, int j, Jeu jeu, PlayScreen screen, World monde, int nbPartie){
		nbEnnemi = 0;
		nbPic = 0;
		this.nbPartie = nbPartie;
		ListeEnnemis = new ArrayList<Integer>();
		this.jeu = jeu;
		this.monde = monde;
		this.screen = screen;

		this.X = i*screen.getTailleBloc()*2*screen.getBlocsParPartieX();
		this.Y = -j*screen.getTailleBloc()*2*screen.getBlocsParPartieY();
		sols = new ArrayList<Sol>();
		setPics(new ArrayList<Pic>());
		ennemis = new ArrayList<Ennemi>();
		
		placementDecors(X, Y);
		placementEnnemis(X, Y);
		
		
	}

	public void placementEnnemis(int X, int Y) {
		for(int i=0; i<screen.getBlocsParPartieX(); i++){
			for(int j=0; j<screen.getBlocsParPartieY(); j++){
				
				posx = X + i*screen.getTailleBloc()*2;
				posy = jeu.V_height + Y - j*screen.getTailleBloc()*2;
				
				if(design[j][i] == 'e'){
					ennemis.add(new Ennemi(jeu, screen, monde, posx, posy, "Ennemi:" + nbEnnemi + ":" + nbPartie));
					nbEnnemi++;
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
					sols.add(new Sol(jeu, screen, monde, posx, posy));
				}
				if(design[j][i] == 'p'){
					getPics().add(new Pic(jeu, screen, monde, posx, posy, "Pic:" + nbPic + ":" + nbPartie));
					nbPic++;
				}
			}
		}
	}

	public void render(SpriteBatch sb) {
		if(!sols.isEmpty()){
			for(int i=0; i<sols.size(); i++){
				sols.get(i).render(sb);
			}
		}
		if(!getPics().isEmpty()){
			for(int i=0; i<getPics().size(); i++){
				getPics().get(i).render(sb);
			}
		}
		if(!ennemis.isEmpty()){
			for(int i=0; i<ennemis.size(); i++){
				ennemis.get(i).render(sb);
			}
		}
	}
}
