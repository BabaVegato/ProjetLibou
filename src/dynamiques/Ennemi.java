package dynamiques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import core.Jeu;
import states.PlayScreen;


public abstract class Ennemi extends Personnage{
	
	protected char state = 'i';
	/*private TextureRegion[] trIdle;
	private TextureRegion[] trWalk;
	private TextureRegion[] trSword;
	private TextureRegion[] trGun;
	private Texture idle;
	private Texture walk;
	private Texture sword;
	private Texture gun;*/
	protected PlayScreen screen;
	protected int Vie;
	protected float VitX;
	protected float VitY;
	
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
	
	public abstract void setAnimation();
	
	public abstract void render(SpriteBatch sb);

	public abstract void atk();
	
	public abstract void mort();

	public abstract void mov(boolean VersLaDroite);
	
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
