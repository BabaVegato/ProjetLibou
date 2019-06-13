package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
        	jeu.setScreen(new Intro(jeu));
        	
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

		jeu.assets.finishLoading();
	}
}
