package dynamiques;

import java.util.ArrayList;

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


public class Joueur extends Personnage{
	
	private static final float DELAYGUN = 2;
	private char state = 'i';
	private char modeTir = 'n'; // n comme normal, b comme bazooka
	private ArrayList<Character> TirsDispos;
	
	private TextureRegion[] trIdle;
	private TextureRegion[] trWalk;
	private TextureRegion[] trSword;
	private TextureRegion[] trGun;
	private Texture idle;
	private Texture walk;
	private Texture sword;
	private Texture gun;
	private int PPM;
	private float Vie = 10;
	private boolean peutTirer = true;
	private float time = 10000;
	private int Rand;
	private boolean aTire = false;
	private boolean sonEpee = true;
	
	public Joueur(Jeu jeu, PlayScreen screen, World monde, int PosX, int PosY){
		super(screen, monde, PosX, PosY, true, "Joueur");
		
		
		this.jeu = jeu;
		this.screen = screen;
		this.PPM = screen.getPPM();
		setDroite(true);
		TirsDispos = new ArrayList<Character>();
		TirsDispos.add('n');
		TirsDispos.add('b');
		
		idle = jeu.assets.get("Assets/idleTab.png");
		walk = jeu.assets.get("Assets/walkTab.png");
		sword = jeu.assets.get("Assets/swordTab.png");
		gun = jeu.assets.get("Assets/gunTab.png");
		trIdle = new TextureRegion[4];
		trWalk = new TextureRegion[6];
		trSword = new TextureRegion[8];
		trGun = new TextureRegion[8];
		
		setAnimation();
	}
	
	public void setAnimation() {
		atk();
		if(isDroite()){
			if(state=='i') {
				tr = TextureRegion.split(idle, 10, 14);
				for(int i=0; i<4; i++){
					trIdle[i] = tr[0][i];
				}
				animation = new Animations(trIdle, false, 15);
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
			/////////////////// GUN ////////////////////////
			if(state=='g'){
				if(modeTir == 'n'){
					tr = TextureRegion.split(gun, 27, 17);
					for(int i=0; i<8; i++){
						trGun[i] = tr[0][i];
					}
					animation = new Animations(trGun, true, 4);
				}
				if(modeTir == 'b'){
					tr = TextureRegion.split(gun, 27, 17);
					for(int i=0; i<8; i++){
						trGun[i] = tr[0][i];
					}
					animation = new Animations(trGun, true, 4);
				}
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
			/////////////////// SWORD /////////////////////
			if(state=='s'){
				tr = TextureRegion.split(sword, 19, 17);
				for(int i=0; i<8; i++){
					trSword[i] = tr[0][i];
					trSword[i].flip(true, false);
				}
				animation = new Animations(trSword, true, 4);
			}
			/////////////////////// GUN //////////////////////////
			if(state=='g'){
				if(modeTir == 'n'){
					tr = TextureRegion.split(gun, 27, 17);
					for(int i=0; i<8; i++){
						trGun[i] = tr[0][i];
						trGun[i].flip(true, false);
					}
					animation = new Animations(trGun, true, 4);
				}
				if(modeTir == 'b'){
					tr = TextureRegion.split(gun, 27, 17);
					for(int i=0; i<8; i++){
						trGun[i] = tr[0][i];
						trGun[i].flip(true, false);
					}
					animation = new Animations(trGun, true, 4);
				}
			}
		}
	}
	public void render(SpriteBatch sb) {
		if(state == 'i'){
			sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY, TailleX*2, TailleY*2);
		}
		if(state == 'w'){
			sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY, (TailleX*2 + 2), TailleY*2);
		}
		if(state == 's'){
			if(isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY - 2, (TailleX*4 - 2), (TailleY*2 + 4));
			}
			if(!isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX - 16, getBody().getPosition().y*PPM-TailleY - 2, TailleX*4 - 2, TailleY*2 + 4);
			}
			if(animation.getCurrentFrame() == 2 && sonEpee){
				sonEpee = false;
				Rand = screen.randInt(1, 3);
				if (Rand==1) screen.getSndSword1().play();
				if (Rand==2) screen.getSndSword2().play();
				if (Rand==3) screen.getSndSword3().play();
			}
			if(animation.getCurrentFrame() == 3) sonEpee = true;
		}
		if(state == 'g'){
			if(isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX, getBody().getPosition().y*PPM-TailleY - 2, TailleX*6 - 6, TailleY*2 + 4);
			}
			if(!isDroite()){
				sb.draw(animation.getFrame(), getBody().getPosition().x*PPM-TailleX - 35, getBody().getPosition().y*PPM-TailleY - 2, TailleX*6 - 6, TailleY*2 + 4);
			}
			if(animation.getCurrentFrame() > 4 ){
				if(!aTire) Tire();
			}
		}
		time += 0.1f;
		if(time > DELAYGUN){
			peutTirer = true;
			aTire = false;
		}
		
		animation.update(1);
		
		if(animation.isFini()){
			state = 'i';
			setAnimation();
			screen.supprEpee();
		}
	}


	private void Tire() {
		Rand = screen.randInt(1, 3);
		if (Rand==1) screen.getSndGun1().play();
		if (Rand==2) screen.getSndGun2().play();
		if (Rand==3) screen.getSndGun3().play();
		if(modeTir == 'n'){
			screen.getProjectiles().add(new TirGun(screen, monde, body.getPosition().x*PPM, body.getPosition().y*PPM, Droite, "TirGunN:" + screen.getNbTir() + ":Projectile"));
		}
		if(modeTir == 'b'){
			screen.getProjectiles().add(new TirGun(screen, monde, body.getPosition().x*PPM, body.getPosition().y*PPM, Droite, "TirGunB:" + screen.getNbTir() + ":Projectile"));
		}
		screen.setNbTir(screen.getNbTir()+1);
		aTire = true;
		peutTirer = false;
		time = 0;
	}

	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	
	public void atk(){
		if (state == 's'){
			//Fixture sensor epee
			fdef = new FixtureDef();
			pshape = new PolygonShape();
			if(isDroite()){
				pshape.setAsBox(TailleX/PPM, (3*TailleY/4)/PPM, new Vector2(2*TailleX/PPM,0), 0);
			}
			else{
				pshape.setAsBox(TailleX/PPM, (3*TailleY/4)/PPM, new Vector2(-2*TailleX/PPM,0), 0);
			}
			fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITENNEMI);
			fdef.shape = pshape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = screen.BITJOUEUR;
			body.createFixture(fdef).setUserData("Epee");
		}
		body.applyForceToCenter(new Vector2(0, -0.1f),true);
	}

	public float getVie() {
		return Vie;
	}

	public void setVie(float vie) {
		Vie = vie;
	}

	public boolean isPeutTirer() {
		return peutTirer;
	}

	public void setPeutTirer(boolean peutTirer) {
		this.peutTirer = peutTirer;
	}

	public ArrayList<Character> getTirsDispos() {
		return TirsDispos;
	}

	public void setTirsDispos(ArrayList<Character> tirsDispos) {
		TirsDispos = tirsDispos;
	}
	public char getModeTir(){
		return modeTir;
	}
	public void setModeTir(char x){
		modeTir = x;
	}
}
