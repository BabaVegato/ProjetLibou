package dynamiques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import core.Jeu;
import handlers.Animations;
import states.PlayScreen;


public class Ennemi extends Personnage{
	
	private char state = 'i';
	private TextureRegion[][] tr;
	private TextureRegion[] trIdle;
	private TextureRegion[] trWalk;
	private TextureRegion[] trSword;
	private TextureRegion[] trGun;
	private Texture idle;
	private Texture walk;
	private Texture sword;
	private Texture gun;
	private PlayScreen screen;
	private int Vie = 3;
	
	public Ennemi(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY, String nom){
		super(screen, monde, PosX, PosY, false, nom);
		
		this.jeu = jeu;
		this.screen = screen;
		setDroite(true);
		
		/*idle = jeu.assets.get("Assets/idleTab.png");
		walk = jeu.assets.get("Assets/walkTab.png");
		sword = jeu.assets.get("Assets/swordTab.png");
		trIdle = new TextureRegion[4];
		trWalk = new TextureRegion[6];
		trSword = new TextureRegion[8];*/
		
		//setAnimation();
	}
	
	public void setAnimation() {
		atk();
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
				animation = new Animations(trSword, true, 4);
			}
			if(state=='g'){
				tr = TextureRegion.split(gun, 27, 17);
				for(int i=0; i<8; i++){
					trGun[i] = tr[0][i];
				}
				animation = new Animations(trGun, true, 4);
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
			if(state=='w'){
				tr = TextureRegion.split(walk, 12, 14);
				for(int i=0; i<6; i++){
					trWalk[i] = tr[0][i];
					trWalk[i].flip(true, false);
				}
				animation = new Animations(trWalk,false, 10);
			}
			if(state=='s'){
				tr = TextureRegion.split(sword, 19, 17);
				for(int i=0; i<8; i++){
					trSword[i] = tr[0][i];
					trSword[i].flip(true, false);
				}
				animation = new Animations(trSword, true, 4);
			}
			if(state=='g'){
				tr = TextureRegion.split(gun, 27, 17);
				for(int i=0; i<8; i++){
					trGun[i] = tr[0][i];
					trGun[i].flip(true, false);
				}
				animation = new Animations(trGun, true, 4);
			}
		}
	}
	public void render(SpriteBatch sb) {
		
		/*if(state == 'i'){
			sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY, TailleX*2, TailleY*2);
		}
		if(state == 'w'){
			sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY, TailleX*2 + 2, TailleY*2);
		}
		if(state == 's'){
			if(isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY - 2, TailleX*4 - 2, TailleY*2 + 4);
			}
			if(!isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX - 16, getBody().getPosition().y-TailleY - 2, TailleX*4 - 2, TailleY*2 + 4);
			}
		}
		if(state == 'g'){
			if(isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX, getBody().getPosition().y-TailleY - 2, TailleX*6 - 6, TailleY*2 + 4);
			}
			if(!isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x-TailleX - 35, getBody().getPosition().y-TailleY - 2, TailleX*6 - 6, TailleY*2 + 4);
			}
		}
		animation.update(1);
		if(animation.isFini()){
			state = 'i';
			setAnimation();
			/////suppr epee
			for(int i=0; i<body.getFixtureList().size; i++){
				if(body.getFixtureList().get(i).getUserData().toString().contains("Epee")){
					body.getFixtureList().removeIndex(i);
				}
			}
		}*/
	}

	public void atk(){
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	public int getVie() {
		return Vie;
	}
	public void setVie(int vie) {
		Vie = vie;
	}
}
