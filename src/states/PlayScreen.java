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
import dynamiques.Ennemi;
import dynamiques.Joueur;
import dynamiques.Projectile;
import dynamiques.TirGun;
import handlers.MonContactList;
import statiques.Pic;


public class PlayScreen implements Screen{

	public short BITGROUND = 4;
	public short BITJOUEUR = 2;
	public short BITOBJET = 8;
	public short BITENNEMI = 16;
	private final Jeu game;
	private Stage stage;
	private SpriteBatch sb;
	private boolean jump;
	private int PPM = 2;
	private int HEIGHT;
	private int WIDTH;
	
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
	private int KEY_GUN = Input.Keys.E;
	
	private String IDNbPartie;
	private String IDNbPartiePic;
	private String IDNbEnnemi;
	private String[] IDEnnemi;
	private String[] IDPic;
	private String IDNbPic;
	
	private Pic pic;
	private Ennemi ennemi;
	private float distanceX;
	private float distanceY;
	private String[] IDTir;
	private String IDNbTir;
	private String IDNbPartieTir;
	private TirGun tir;
	private int nbTir = 0;

	
	public PlayScreen(final Jeu game) {
		
		HEIGHT = game.V_height/getPPM();
		WIDTH = game.V_height/getPPM();
		
		//Sons
		SndJump = game.assets.get("Assets/SndJump.mp3");
		
		//Setup
		Monde = new World(new Vector2(0, -120.81f/PPM), true);
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
		projectiles = new ArrayList<Projectile>();
		
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
		
		font.draw(sb, "Vie : " + joueur.getVie(), 100, 100);
		cam.update();
		
		joueur.render(sb);
		
		for(int i=0; i<projectiles.size(); i++){
			projectiles.get(i).render(sb);
		}
		
		niveau1.render(sb);
		
		sb.end();//////////////////////////////
		
		debugR.render(Monde, cam.combined.cpy().scl(getPPM()));
		
		processCameraMovement();
		
		stage.act(delta);
		stage.draw();
		
		
		Monde.step(1/40f, 6, 2);
		
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
		jump = false;
			
		if ( (Gdx.input.isKeyJustPressed(KEY_JUMP) || Gdx.input.isKeyJustPressed(KEY_JUMP_2)) && contList.isJoueurSol()) {
            y=70;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
        	SndJump.play();
        	jump = true;
        }
		processMov(x, y, 25f);
		processAtk();
		processDegats();
		processPicsEtProj();
		EnnemiMov();
		//System.out.println(joueur.getState());
   }

	public void processPicsEtProj(){
		//////////// PICS ///////////////
		if(contList.isPicActive()){
			IDPic = contList.getIDPic().split(":");
			IDNbPic = IDPic[1];
			IDNbPartiePic = IDPic[2];
			niveau1.GestionPic(IDNbPic, IDNbPartiePic);
			
			contList.setPicActive(false);
		}
		for(int i=0; i<niveau1.getParties().size(); i++){
			for(int j=0; j<niveau1.getParties().get(i).getPics().size(); j++){
				pic = niveau1.getParties().get(i).getPics().get(j);
				if(pic.isDangereux()){
					distanceX = Math.abs(pic.getBody().getPosition().x - joueur.getBody().getPosition().x);
					if(distanceX < 2*TailleBloc/3){
						distanceY = Math.abs(pic.getBody().getPosition().y - joueur.getBody().getPosition().y);
						if(distanceY < 3*TailleBloc/2){
							System.out.println("Mort");
						}
					}
				}
			}
		}
		//////////////// PROJ ////////////////
		if(contList.isTirGunMur()){
			IDTir = contList.getIDTir().split(":");
			IDNbTir = IDTir[1];
			projectiles.get(Integer.parseInt(IDNbTir)).suppr();
			
			contList.setTirGunMur(false);
		}
		
	}
	
	private void ScoreScreen() {
		//stage.addActor(actor);
	}

	public void processDegats() {
		
		//////////// EPEE ///////////
		if(contList.isDegatsAGerer()){
			//IDEnnemi = [TYPE ; Numero ; Partie]
			IDEnnemi = contList.getIDEnnemi().split(":");
			IDNbEnnemi = IDEnnemi[1];
			IDNbPartie = IDEnnemi[2];
			
			System.out.println("IDNbEnnemi : " + IDNbEnnemi + "/ IDNbPartie : " + IDNbPartie);
			
			niveau1.GestionVie(IDNbEnnemi, IDNbPartie, 2);
			
			contList.setDegatsAGerer(false);
		}
		//////////// GUN ////////////
		for(int i=0; i<niveau1.getParties().size(); i++){
			for(int j=0; j<niveau1.getParties().get(i).getEnnemis().size(); j++){
				
				ennemi = niveau1.getParties().get(i).getEnnemis().get(j);
				distanceX = ennemi.getBody().getPosition().x - joueur.getBody().getPosition().x;
				
				distanceY = Math.abs(ennemi.getBody().getPosition().y - joueur.getBody().getPosition().y);
				
				if(distanceY < 2.2f*TailleBloc){
					if(distanceX < 0) ennemi.mov(true);
					else ennemi.mov(false);
				}
			}
		}
	}

	
	
	public void EnnemiMov(){
		for(int i=0; i<niveau1.getParties().size(); i++){
			for(int j=0; j<niveau1.getParties().get(i).getEnnemis().size(); j++){
				
				ennemi = niveau1.getParties().get(i).getEnnemis().get(j);
				distanceX = ennemi.getBody().getPosition().x - joueur.getBody().getPosition().x;
			
				distanceY = Math.abs(ennemi.getBody().getPosition().y - joueur.getBody().getPosition().y);
				
				if(distanceY < 2.2f*TailleBloc){
					if(distanceX < 0) ennemi.mov(true);
					else ennemi.mov(false);
				}
			}
		}
	}
	
	public void processMov(float x, float y, float v){
		if (Gdx.input.isKeyPressed(KEY_RIGHT) && joueur.getBody().getLinearVelocity().x <= v) {
        	x+=v;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
            if(!joueur.isDroite()) {
        		joueur.setDroite(true);
        		if(joueur.getState()=='s'){
        			joueur.setState('w');
        			/////suppr epee
        			for(int i=0; i<joueur.getBody().getFixtureList().size; i++){
        				if(joueur.getBody().getFixtureList().get(i).getUserData().toString().contains("Epee")){
        					joueur.getBody().destroyFixture(joueur.getBody().getFixtureList().get(i));
        				}
        			}
        		}
        		if(joueur.getState()=='g'){
        			joueur.setState('w');
        		}
        		joueur.setAnimation();
        	}
            if(contList.isJoueurSol() && joueur.getState()=='i'){
            	joueur.setState('w');
            	joueur.setAnimation();
            }
        }
        if (Gdx.input.isKeyPressed(KEY_LEFT) && joueur.getBody().getLinearVelocity().x >= -v) {
        	x-=v;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
            if(joueur.isDroite()) {
        		joueur.setDroite(false);
        		if(joueur.getState()=='s'){
        			joueur.setState('w');
        			/////suppr epee
        			for(int i=0; i<joueur.getBody().getFixtureList().size; i++){
        				if(joueur.getBody().getFixtureList().get(i).getUserData().toString().contains("Epee")){
        					joueur.getBody().destroyFixture(joueur.getBody().getFixtureList().get(i));
        				}
        			}
        		}
        		if(joueur.getState()=='g'){
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
			if(joueur.getState() != 's'){
				joueur.setState('s');
				joueur.setAnimation();
			}
		}
		if(Gdx.input.isKeyJustPressed(KEY_GUN) && joueur.isPeutTirer()){
			if(joueur.getState() != 'g'){
				joueur.setState('g');
				joueur.setAnimation();
			}
			/////suppr epee
			for(int i=0; i<joueur.getBody().getFixtureList().size; i++){
				if(joueur.getBody().getFixtureList().get(i).getUserData().toString().contains("Epee")){
					joueur.getBody().destroyFixture(joueur.getBody().getFixtureList().get(i));
				}
			}
			if(joueur.getTire()){
				projectiles.add(new TirGun(this, Monde, joueur.getBody().getPosition().x*PPM, joueur.getBody().getPosition().y*PPM, joueur.isDroite(), "TirGun:" + nbTir + ":Projectile"));
				joueur.setTire(false);
				joueur.setPeutTirer(false);
				nbTir++;
			}
		}
	}
	
	public void processCameraMovement(){
		posCameraDesired.x=joueur.getBody().getPosition().x*PPM;
		posCameraDesired.y=joueur.getBody().getPosition().y*PPM;
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
	public int getPPM() {
		return PPM;
	}
	public void setPPM(int pPM) {
		PPM = pPM;
	}
}