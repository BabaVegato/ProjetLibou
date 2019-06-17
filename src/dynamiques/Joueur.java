package dynamiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import core.Jeu;
import handlers.Animations;
import states.PlayScreen;


public class Joueur extends Personnage{
	
	private char state = 'i';
	private TextureRegion[][] tr;
	private TextureRegion[] trIdle;
	private TextureRegion[] trWalk;
	private TextureRegion[] trSword;
	private Texture idle;
	private Texture walk;
	private Texture sword;
	
	public Joueur(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY){
		super(screen, monde, PosX, PosY);
		
		this.jeu = jeu;
		setDroite(true);
		
		idle = jeu.assets.get("Assets/idleTab.png");
		walk = jeu.assets.get("Assets/walkTab.png");
		sword = jeu.assets.get("Assets/swordTab.png");
		trIdle = new TextureRegion[4];
		trWalk = new TextureRegion[6];
		trSword = new TextureRegion[8];
		
		
		
		setAnimation();
	}
	
	public void setAnimation() {
		if(isDroite()){
			if(state=='i') {
				tr = TextureRegion.split(idle, 10, 14);
				for(int i=0; i<4; i++){
					trIdle[i] = tr[0][i];
				}
				animation = new Animations(trIdle, false, 10);
			}
			if(state=='w'){
				tr = TextureRegion.split(walk, 12, 14);
				for(int i=0; i<6; i++){
					trWalk[i] = tr[0][i];
				}
				animation = new Animations(trWalk,false, 10);
			}
			if(state=='s'){
				tr = TextureRegion.split(sword, 19, 17);
				for(int i=0; i<8; i++){
					trSword[i] = tr[0][i];
				}
				animation = new Animations(trSword, true, 10);
			}
		}
		if(!isDroite()){
			if(state=='i'){
				tr = TextureRegion.split(idle, 10, 14);
				for(int i=0; i<4; i++){
					trIdle[i] = tr[0][i];
					trIdle[i].flip(true, false);
				}
				animation = new Animations(trIdle,false, 10);
			}
			if(state=='s'){
				tr = TextureRegion.split(sword, 19, 17);
				for(int i=0; i<8; i++){
					trSword[i] = tr[0][i];
					trSword[i].flip(true, false);
				}
				animation = new Animations(trSword, true, 10);
			}
		}
	}
	public void render(SpriteBatch sb) {
		if(state == 'i' || state == 'w'){
			sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY, TailleX*2, TailleY*2);
		}
		else{
			sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY - 2, TailleX*4 - 2, TailleY*2 + 2);
		}
		animation.update(1);
		if(animation.isFini()){
			state = 'i';
			setAnimation();
		}
	}


	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}
}
