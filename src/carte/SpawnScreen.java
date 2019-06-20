package carte;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import dynamiques.Ennemi;
import states.PlayScreen;
import statiques.Pic;
import statiques.Sol;

	public class SpawnScreen extends Partie{

		//Un simple layout pour un genre d'écran de départ, avec genre une porte sur le côté pour commencer
		//Je suis pas sûr de comprendre exactement comment transitionner de cet écran au jeu par contre
		private char[][] design = {
				{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'},
				{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'},
				{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
				{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
				{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 's'},
				{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'},
				{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'}, 
				{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'}};

		public SpawnScreen(int i, int j, Jeu jeu, PlayScreen screen, World monde, int nbPartie){
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
			setEnnemis(new ArrayList<Ennemi>());
			
			placementDecors(X, Y);
			placementEnnemis(X, Y);
			
			
		}


		public void placementDecors(int X, int Y) {
			for(int i=0; i<screen.getBlocsParPartieX(); i++){
				for(int j=0; j<screen.getBlocsParPartieY(); j++){
					
					posx = X + i*screen.getTailleBloc()*2;
					posy = jeu.V_height + Y - j*screen.getTailleBloc()*2;
					
					if(design[j][i] == 's'){
						sols.add(new Sol(jeu, screen, monde, posx, posy));
					
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
			/*if(!getPics().isEmpty()){
				for(int i=0; i<getPics().size(); i++){
					getPics().get(i).render(sb);
				}
			}
			if(!getEnnemis().isEmpty()){
				for(int i=0; i<getEnnemis().size(); i++){
					getEnnemis().get(i).render(sb);
				}
			}*/
		}


		@Override
		public void placementEnnemis(int X, int Y) {
			
		}
	}

	
}
