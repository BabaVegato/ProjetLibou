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

	private ArrayList<Decors> decors;
	private ArrayList<Ennemi> ennemis;
	private Jeu jeu;
	private World monde;
	private PlayScreen screen;
	private int X, Y;
	private int PPM;
	private int posx, posy;
	private char[][] design = {
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'s', '0', '0', '0', '0', '0', '0', 'e', 'e', '0', '0', '0', '0', '0', '0'},
			{'s', '0', '0', '0', 'p', 'p', 'p', 'p', 'p', '0', '0', '0', '0', '0', '0'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}, 
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', 'p', 'p', '0', 'p', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', 's', 's', 's', 's', 's', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', 's', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's'}};

	public lvl1part1(int i, int j, Jeu jeu, PlayScreen screen, World monde){
		this.jeu = jeu;
		this.monde = monde;
		this.screen = screen;
		PPM = screen.getPPM();
		this.X = i*screen.getTailleBloc()*2*screen.getBlocsParPartieX();
		this.Y = -j*screen.getTailleBloc()*2*screen.getBlocsParPartieY();
		decors = new ArrayList<Decors>();
		ennemis = new ArrayList<Ennemi>();
		
		initDesign();
		placementDecors(X, Y);
		placementEnnemis(X, Y);
		
		
	}

	private void initDesign() {
		
	}

	public void placementEnnemis(int X, int Y) {
		for(int i=0; i<screen.getBlocsParPartieX(); i++){
			for(int j=0; j<screen.getBlocsParPartieY(); j++){
				
				posx = X + i*screen.getTailleBloc()*2/PPM;
				posy = jeu.V_height + Y - j*screen.getTailleBloc()*2/PPM;
				
				if(design[j][i] == 'e'){
					ennemis.add(new Ennemi(jeu, screen, monde, posx, posy));
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
					decors.add(new Sol(jeu, screen, monde, posx, posy));
				}
				if(design[j][i] == 'p'){
					decors.add(new Pic(jeu, screen, monde, posx, posy));
				}
			}
		}
	}

	public void render(SpriteBatch sb) {
		if(!decors.isEmpty()){
			for(int i=0; i<decors.size(); i++){
				decors.get(i).render(sb);
			}
		}
		if(!ennemis.isEmpty()){
			for(int i=0; i<ennemis.size(); i++){
				ennemis.get(i).render(sb);
			}
		}
	}
}
