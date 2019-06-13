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
import core.Jeu;
import dynamiques.Joueur;
import dynamiques.Projectile;


public class PlayScreen implements Screen{


	public short BITGROUND = 4;
	public short BITPLAYER = 2;
	public short BITOBJET = 8;
	private final Jeu game;
	private Stage stage;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private World Monde;
	private Box2DDebugRenderer debugR;
	private MonContactList contList;
	private BitmapFont font = new BitmapFont();
	
	private Joueur joueur;
	
	private ArrayList<Decors> decors = new ArrayList<Decors>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public PlayScreen(final Jeu game) {
		
		Monde = new World(new Vector2(0, -20.81f), true);
		
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		
		//Sons
		
		
		//Setup
		batch = new SpriteBatch();
		debugR = new Box2DDebugRenderer();
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 600, 600);
		
		contList = new MonContactList();
		
		Monde.setContactListener(contList);
		
		//Decors
		
		decors = new ArrayList<Decors>();
		
		//Personnages
		joueur = new Joueur(game, this, Monde, game.V_width/2, game.V_height/2);
		
		//Objets
		//objets = new ArrayList<Objet>();
		//projectiles = new ArrayList<Projectile>();
		
	}

	public void dispose() {
		Monde.dispose();
		batch.dispose();
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
		game.batch.setProjectionMatrix(cam.combined);
		
		
		game.batch.begin();
		
		font.draw(game.batch, "Vie : 10", 100, 100);
		joueur.render(game.batch);
		
		game.batch.end();
		
		
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
		
   }
}