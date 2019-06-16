package states;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import handlers.MonContactList;
import statiques.Decors;
import statiques.Sol;
import core.Jeu;
import dynamiques.Joueur;
import dynamiques.Projectile;


public class PlayScreen implements Screen{


	public short BITGROUND = 4;
	public short BITJOUEUR = 2;
	public short BITOBJET = 8;
	private final Jeu game;
	private Stage stage;
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private World Monde;
	private Box2DDebugRenderer debugR;
	private MonContactList contList;
	private BitmapFont font = new BitmapFont();
	
	private Sound SndJump;
	
	private Joueur joueur;
	
	private ArrayList<Decors> decors = new ArrayList<Decors>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	private int KEY_JUMP = Input.Keys.Z;
	private int KEY_JUMP_2 = Input.Keys.SPACE;
	private int KEY_RIGHT = Input.Keys.D;
	private int KEY_LEFT = Input.Keys.Q;

	
	public PlayScreen(final Jeu game) {
		
		Monde = new World(new Vector2(0, -20.81f), true);
		
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		this.sb = game.batch;
		//Sons
		SndJump = game.assets.get("Assets/SndJump.mp3");
		
		//Setup
		sb = new SpriteBatch();
		debugR = new Box2DDebugRenderer();
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, game.V_width, game.V_height);
		game.batch.setProjectionMatrix(cam.combined);
		
		contList = new MonContactList();
		
		Monde.setContactListener(contList);
		
		//Decors
		decors = new ArrayList<Decors>();
		decors.add(new Sol(game, this, Monde, game.V_width/2, game.V_height/4));
		
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

		cam.update();
		sb.setProjectionMatrix(cam.combined);
		
		
		sb.begin();////////////////////////////
		
		font.draw(sb, "Vie : 10", 100, 100);
		
		joueur.render(sb);
		
		for(int i=0; i<decors.size(); i++){
			decors.get(i).render(sb);
		}
		
		sb.end();//////////////////////////////
		
		
		debugR.render(Monde, cam.combined);
		
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
		
		if ( (Gdx.input.isKeyJustPressed(KEY_JUMP) || Gdx.input.isKeyJustPressed(KEY_JUMP_2)) /*&& personnage pas en l'air*/) {
            y+=300;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
        	SndJump.play();
        }
        if (Gdx.input.isKeyPressed(KEY_RIGHT) && joueur.getBody().getLinearVelocity().x <= 20f) {
        	x+=10;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
            if(!joueur.isDroite()) {
        		joueur.setDroite(true);
        		joueur.setAnimation();
        	}
        }
        if (Gdx.input.isKeyPressed(KEY_LEFT) && joueur.getBody().getLinearVelocity().x >= -20f) {
        	x-=10;
            joueur.getBody().setLinearVelocity(new Vector2(x, y));
            if(joueur.isDroite()) {
        		joueur.setDroite(false);
        		joueur.setAnimation();
        	}
        }
   }
}