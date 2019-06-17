package states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import carte.Niveau1;
import core.Jeu;
import dynamiques.Joueur;
import dynamiques.Projectile;
import handlers.MonContactList;


public class PlayScreen implements Screen{


	public short BITGROUND = 4;
	public short BITJOUEUR = 2;
	public short BITOBJET = 8;
	private final Jeu game;
	private Stage stage;
	private SpriteBatch sb;
	
	private OrthographicCamera cam;
	private Vector3 posCameraDesired;
	
	private World Monde;
	private Box2DDebugRenderer debugR;
	private MonContactList contList;
	private BitmapFont font = new BitmapFont();
	
	private Niveau1 niveau1;
	private int TailleBloc = 20;
	private int BlocsParPartieX = 15;
	private int BlocsParPartieY = 20;
	
	private Sound SndJump;
	private Joueur joueur;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	private int KEY_JUMP = Input.Keys.Z;
	private int KEY_JUMP_2 = Input.Keys.SPACE;
	private int KEY_RIGHT = Input.Keys.D;
	private int KEY_LEFT = Input.Keys.Q;
	private int KEY_SWORD = Input.Keys.F;

	
	public PlayScreen(final Jeu game) {
		
		//Sons
		SndJump = game.assets.get("Assets/SndJump.mp3");
		
		//Setup
		Monde = new World(new Vector2(0, -25.81f), true);
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		this.sb = game.batch;
		sb = new SpriteBatch();
		debugR = new Box2DDebugRenderer();
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, game.V_width, game.V_height);
		game.batch.setProjectionMatrix(cam.combined);
		contList = new MonContactList();
		Monde.setContactListener(contList);
		
		posCameraDesired = new Vector3(game.V_width, game.V_height, 0);
		
		//Niveaux
		niveau1 = new Niveau1(game, Monde, this);
		
		//Personnages
		joueur = new Joueur(game, this, Monde, game.V_width/2, game.V_height/2);
		
		//Objets
		//objets = new ArrayList<Objet>();
		//projectiles = new ArrayList<Projectile>();
		
	}

	public void dispose() {
		Monde.dispose();
		sb.dispose();
		stage.dispose();
		debugR.dispose();
	}

	public void hide() {
		
	}

	public void pause() {
		
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sb.setProjectionMatrix(cam.combined);
		
		
		sb.begin();////////////////////////////
		
		font.draw(sb, "Vie : 10", 100, 100);
		cam.update();
		joueur.render(sb);
		
		niveau1.render(sb);
		
		sb.end();//////////////////////////////
		
		debugR.render(Monde, cam.combined);
		
		processCameraMovement();
		
		stage.act(delta);
		stage.draw();
		
		
		Monde.step(1/20f, 6, 2);
		
		handleInput(delta);
		
	}

	public void resize(int width, int height) {
	}

	public void resume() {
		
	}

	public void show() {
		//Bgm.play();
	}
	
	public void handleInput(float dt){
		float x = joueur.getBody().getLinearVelocity().x;
		float y = joueur.getBody().getLinearVelocity().y;
			
		if ( (Gdx.input.isKeyJustPressed(KEY_JUMP) || Gdx.input.isKeyJustPressed(KEY_JUMP_2)) && contList.isJoueurSol()) {
            y+=500;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
        	SndJump.play();
        }
		processMov(x, y);
		processAtk();
		//System.out.println(joueur.getState());
   }
	
	public void processMov(float x, float y){
		if (Gdx.input.isKeyPressed(KEY_RIGHT) && joueur.getBody().getLinearVelocity().x <= 20f) {
        	x+=10;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
            if(!joueur.isDroite()) {
        		joueur.setDroite(true);
        		if(joueur.getState()=='s'){
        			joueur.setState('w');
        		}
        		joueur.setAnimation();
        	}
            if(contList.isJoueurSol() && joueur.getState()=='i'){
            	joueur.setState('w');
            	joueur.setAnimation();
            }
        }
        if (Gdx.input.isKeyPressed(KEY_LEFT) && joueur.getBody().getLinearVelocity().x >= -20f) {
        	x-=10;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
            if(joueur.isDroite()) {
        		joueur.setDroite(false);
        		if(joueur.getState()=='s'){
        			joueur.setState('w');
        		}
        		joueur.setAnimation();
        	}
            if(contList.isJoueurSol() && joueur.getState()=='i'){
            	joueur.setState('w');
            	joueur.setAnimation();
            }
        }
        if(!Gdx.input.isKeyPressed(KEY_LEFT) && !Gdx.input.isKeyPressed(KEY_RIGHT) && joueur.getState()=='w'){
        	joueur.setState('i');
        	joueur.setAnimation();
        }
	}
	
	public void processAtk(){
		if(Gdx.input.isKeyJustPressed(KEY_SWORD)){
			joueur.setState('s');
			joueur.setAnimation();
		}
	}
	
	public void processCameraMovement(){
		posCameraDesired.x=joueur.getBody().getPosition().x;
		posCameraDesired.y=joueur.getBody().getPosition().y;
		cam.position.lerp(posCameraDesired,0.1f);
	}

	public int getTailleBloc() {
		return TailleBloc;
	}
	public void setTailleBloc(int tailleBloc) {
		TailleBloc = tailleBloc;
	}
	public int getBlocsParPartieX() {
		return BlocsParPartieX;
	}
	public void setBlocsParPartieX(int blocsParPartieX) {
		BlocsParPartieX = blocsParPartieX;
	}
	public int getBlocsParPartieY() {
		return BlocsParPartieY;
	}
	public void setBlocsParPartieY(int blocsParPartieY) {
		BlocsParPartieY = blocsParPartieY;
	}
}