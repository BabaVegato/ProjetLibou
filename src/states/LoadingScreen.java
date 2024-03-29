package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import core.Jeu;

public class LoadingScreen implements Screen{

	private ShapeRenderer sr;
	private final Jeu jeu;
	private float progress;
	private String Loading = "Chargement ...";
	
	public LoadingScreen(final Jeu jeu) {
		this.jeu = jeu;
		this.sr = new ShapeRenderer();
		queueasset();
	}
	public void dispose() {
		sr.dispose();
	}

	public void hide() {
		
	}

	public void pause() {
		
	}
	private void update(float delta) {
		progress = MathUtils.lerp(progress, jeu.assets.getProgress(), .1f);
        if (jeu.assets.update() && progress >= jeu.assets.getProgress() - .001f) {
        	jeu.setScreen(new MenuScreen(jeu));
        	
        }
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		jeu.batch.begin();
		jeu.font.draw(jeu.batch, Loading, 20, 20);
		jeu.batch.end();
	}

	public void resize(int width, int height) {
		
	}

	public void resume() {
		
	}

	public void show() {
		this.progress = 0f;
		
	}
	public void queueasset() {
		jeu.assets.load("Assets\\goToLevel.mp3", Sound.class);
		jeu.assets.load("Assets\\SndPlayH.mp3", Sound.class);
		jeu.assets.load("Assets\\SndJump.mp3", Sound.class);
		jeu.assets.load("Assets\\SndHurt.wav", Sound.class);
		
		jeu.assets.load("Assets\\BTNPlayH.png", Texture.class);
		jeu.assets.load("Assets\\BTNPlayC.png", Texture.class);
		jeu.assets.load("Assets\\BTNPlay.png", Texture.class);
		
		jeu.assets.load("Assets\\idleTab.png", Texture.class);
		jeu.assets.load("Assets\\walkTab.png", Texture.class);
		jeu.assets.load("Assets\\swordTab.png", Texture.class);
		jeu.assets.load("Assets\\gunTab.png", Texture.class);
		
		jeu.assets.load("Assets\\Carre.png", Texture.class);
		jeu.assets.load("Assets\\picTab.png", Texture.class);
		jeu.assets.load("Assets\\blocDetruitTab.png", Texture.class);
		
		jeu.assets.load("Assets\\SndChangeTir.wav", Sound.class);
		jeu.assets.load("Assets\\SndJumpJumper.wav", Sound.class);
		jeu.assets.load("Assets\\SndGun1.wav", Sound.class);
		jeu.assets.load("Assets\\SndGun2.wav", Sound.class);
		jeu.assets.load("Assets\\SndGun3.wav", Sound.class);
		jeu.assets.load("Assets\\SndSword1.wav", Sound.class);
		jeu.assets.load("Assets\\SndSword2.wav", Sound.class);
		jeu.assets.load("Assets\\SndSword3.wav", Sound.class);
		
		jeu.assets.load("Assets\\jumperTab.png", Texture.class);
		jeu.assets.load("Assets\\tirSentinelle.png", Texture.class);
		jeu.assets.load("Assets\\tourelleTab.png", Texture.class);
		jeu.assets.load("Assets\\spinnerTab.png", Texture.class);
		
		jeu.assets.finishLoading();
	}
}
