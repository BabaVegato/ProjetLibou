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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
import handlers.MonContactList;
import statiques.Pic;


public class PlayScreen implements Screen{

	public short BITGROUND = 4;
	public short BITJOUEUR = 2;
	public short BITOBJET = 8;
	public short BITENNEMI = 16;
	private int PPM = 2;
	private int BlocsParPartieX = 15, BlocsParPartieY = 20, TailleBloc = 20;
	
	private final Jeu game;
	private Stage stage;
	private SpriteBatch sb;
	private boolean jump;
	
	private OrthographicCamera cam;
	private Vector3 posCameraDesired;
	private World Monde;
	private Box2DDebugRenderer debugR;
	private MonContactList contList;
	private BitmapFont font = new BitmapFont();
	private ShapeRenderer sr;
	
	private Niveau1 niveau1;
	private Joueur joueur;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	private int KEY_JUMP = Input.Keys.Z;
	private int KEY_JUMP_2 = Input.Keys.SPACE;
	private int KEY_RIGHT = Input.Keys.D;
	private int KEY_LEFT = Input.Keys.Q;
	private int KEY_SWORD = Input.Keys.F;
	private int KEY_GUN = Input.Keys.E;
	private int KEY_CHANGETIR = Input.Keys.A;
	
	private float distanceX;
	private float distanceY;
	private int Rand;
	private float BouleR = 0, BouleG = 255, BouleB = 0, alphaBoule = 0;
	private Vector2 BoulePos;
	private float tempsBoule = 0.00001f;
	private float VieNormee;
	private float tempsDegats = 1;
	private float vibr = 0.2f;
	
	private Pic pic;
	private String[] IDPic;
	private String IDNbPic, IDNbPartiePic;
	private Ennemi ennemi;
	private String IDNbPartie, IDNbEnnemi;
	private String[] IDEnnemi;
	private String[] IDTir;
	private String IDNbTir;
	private int nbTir = 0;
	
	private Sound SndChangeTir;
	private Sound SndJumpJumper;
	private Sound SndJump;
	private Sound SndHurt;
	private Sound SndGun1, SndGun2, SndGun3;
	private Sound SndSword1, SndSword2, SndSword3;
	private float VieAvtContact;
	private boolean vieAEtudier = true;
	private String[] IDITEM;
	private String IDNbItem;
	private String IDNbPartieItem;
	private char modeTir;
	private ArrayList<Character> TirsDispos;
	private int IndexModeTir;
	private String[] IDDecors;
	private String IDNbPartieDecors;
	private String IDNbDecors;
	private String IDDecorsGenre;
	

	
	public PlayScreen(final Jeu game) {
		
		BoulePos = new Vector2(game.V_width/2,game.V_height/2 + 50);
		
		//Sons
		SndChangeTir = game.assets.get("Assets/SndChangeTir.wav");
		SndJump = game.assets.get("Assets/SndJump.mp3");
		SndJumpJumper = game.assets.get("Assets/SndJumpJumper.wav");
		SndHurt = game.assets.get("Assets/SndHurt.wav");
		SndGun1 = game.assets.get("Assets/SndGun1.wav");
		SndGun2 = game.assets.get("Assets/SndGun2.wav");
		SndGun3 = game.assets.get("Assets/SndGun3.wav");
		SndSword1 = game.assets.get("Assets/SndSword1.wav");
		SndSword2 = game.assets.get("Assets/SndSword2.wav");
		SndSword3 = game.assets.get("Assets/SndSword3.wav");
		
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
		sr = new ShapeRenderer();
		
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
		
		for(int i=0; i<getProjectiles().size(); i++){
			getProjectiles().get(i).render(sb);
		}
		
		niveau1.render(sb);
		
		sb.end();//////////////////////////////
		
		debugR.render(Monde, cam.combined.cpy().scl(getPPM()));
		
		processCameraMovement();
		
		stage.act(delta);
		stage.draw();
		
		//BOULE DE VIE
		sr.begin(ShapeType.Filled);
		sr.setColor(BouleR/255, BouleG/255, BouleB/255, 1);
		sr.circle(BoulePos.x, BoulePos.y, 10);
		sr.end();
		//////////////
		
		
		Monde.step(1/40f, 6, 2);
		
		handleInput(delta);
		
		UpdateBouleDeVie(1f, vibr);
		
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
		processChangeTir();
		processDegats();
		processPicsEtProj();
		EnnemiMov();
		//System.out.println(joueur.getState());
   }

	private void processChangeTir() {
		if(Gdx.input.isKeyJustPressed(KEY_CHANGETIR)){
			SndChangeTir.play();
			modeTir = joueur.getModeTir();
			TirsDispos = joueur.getTirsDispos();
			IndexModeTir = TirsDispos.indexOf(modeTir);
			
			if(IndexModeTir < TirsDispos.size()-1) IndexModeTir++;
			else IndexModeTir = 0;
			joueur.setModeTir(TirsDispos.get(IndexModeTir));
			
			System.out.println(modeTir);
		}
		
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
		//////////////// PROJECTILES ////////////////
		if(contList.isTirGunMur()){
			IDTir = contList.getIDTir().split(":");
			IDNbTir = IDTir[1];
			getProjectiles().get(Integer.parseInt(IDNbTir)).suppr();
			if(IDTir[0].contains("TirGunB")){
				IDDecors = contList.getIDDecors().split(":");
				IDDecorsGenre = IDDecors[0];
				IDNbDecors = IDDecors[1];
				IDNbPartieDecors = IDDecors[2];
				
				niveau1.DestructionDecors(IDDecorsGenre,IDNbPartieDecors, IDNbDecors);
			}
			
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
			
			niveau1.GestionVie(IDNbEnnemi, IDNbPartie, 2);
			
			tempsDegats += Gdx.graphics.getDeltaTime();
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
		////////JOUEUR EN CONTACT AVEC ENNEMI /////////
		tempsDegats += Gdx.graphics.getDeltaTime();
		if(contList.isJoueurEnnemi() && tempsDegats > 1){
			//IDEnnemi = [TYPE ; Numero ; Partie]
			IDEnnemi = contList.getIDEnnemi().split(":");
			IDNbEnnemi = IDEnnemi[1];
			IDNbPartie = IDEnnemi[2];
			
			//On récupère la vie avant le dégat
			if(vieAEtudier){
				VieAvtContact = joueur.getVie();
				SndHurt.play();
			}
			vieAEtudier = false;
			
			//Retire graduellement de la vie
			if(VieAvtContact - 3 < joueur.getVie()){
				joueur.setVie(joueur.getVie()-0.05f);
				vibr = 2f;
			}
			else{
				joueur.setVie(Math.round(joueur.getVie()));
				tempsDegats = 0;
				vieAEtudier = true;
				contList.setJoueurEnnemi(false);
				vibr = 0.2f;
			}
			System.out.println(joueur.getVie());
		}
		
		////////JOUEUR PRENDS BONUSVIE /////////
		if(contList.isJoueurItem()){
			//IDEnnemi = [TYPE ; Numero ; Partie]
			IDITEM = contList.getIDItem().split(":");
			IDNbItem = IDITEM[1];
			IDNbPartieItem = IDITEM[2];
			
			niveau1.GestionItem(IDNbItem, IDNbPartieItem);
			
			//Remet 3 points de vie
			joueur.setVie(joueur.getVie()+3);
			System.out.println(joueur.getVie());
			contList.setJoueurItem(false);
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
		////////////////////////////////////////
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
			Rand = randInt(1, 3);
			if (Rand==1) SndSword1.play();
			if (Rand==2) SndSword2.play();
			if (Rand==3) SndSword3.play();
			if(joueur.getState() != 's'){
				joueur.setState('s');
				joueur.setAnimation();
			}
		}
		if(Gdx.input.isKeyJustPressed(KEY_GUN) && joueur.isPeutTirer()){
			joueur.setState('g');
			joueur.setAnimation();
			supprEpee();
		}
	}
	
	public void processCameraMovement(){
		posCameraDesired.x=joueur.getBody().getPosition().x*PPM;
		posCameraDesired.y=joueur.getBody().getPosition().y*PPM;
		cam.position.lerp(posCameraDesired,0.1f);
	}
	
	public int randInt(int Min, int Max){
		//prend un int dans [Min;Max]
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
	
	public void UpdateBouleDeVie(float tempsLerp, float vibrations){
		
		//////////////////POSITION///////////////////
		if(alphaBoule < 1){
			tempsBoule += Gdx.graphics.getDeltaTime();
			alphaBoule = tempsLerp/tempsBoule;
			
			BoulePos.lerp(new Vector2(joueur.getBody().getPosition().x*PPM, joueur.getBody().getPosition().y*PPM + 50), alphaBoule);
		}
		Rand = randInt(0, 3);
		if(Rand == 0)BoulePos.x += vibr;
		if(Rand == 1)BoulePos.x -= vibr;
		if(Rand == 2)BoulePos.y += vibr;
		if(Rand == 3)BoulePos.y -= vibr;
		
		/////////////////COULEUR///////////////////
		VieNormee = joueur.getVie()/10;
		BouleR = (1-VieNormee)*255;
		BouleG = VieNormee*255;
		
	}
	
	public void supprEpee(){
		for(int i=0; i<joueur.getBody().getFixtureList().size; i++){
			if(joueur.getBody().getFixtureList().get(i).getUserData().toString().contains("Epee")){
				joueur.getBody().destroyFixture(joueur.getBody().getFixtureList().get(i));
			}
		}
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
	public Sound getSndJumpJumper() {
		return SndJumpJumper;
	}
	public Joueur getJoueur(){
		return joueur;
	}

	public Sound getSndGun1() {
		return SndGun1;
	}

	public Sound getSndGun2() {
		return SndGun2;
	}

	public Sound getSndGun3() {
		return SndGun3;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public int getNbTir() {
		return nbTir;
	}

	public void setNbTir(int nbTir) {
		this.nbTir = nbTir;
	}
}