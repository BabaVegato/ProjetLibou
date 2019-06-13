package states;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

import core.Jeu;

public class MenuScreen implements Screen{
	
	private Jeu game;
	private Stage stage;
	private Boolean PlayisHovered;
	private Boolean Clicked;
	private Sound SndPlayH;
	private Sound GoToLevel;
	private Image ImgPlay;
	private Image ImgPlayH;
	private Image ImgPlayC;


	public MenuScreen(final Jeu game) {
		
		Clicked = false;
		PlayisHovered = false;
		
		this.game = game;
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		Gdx.input.setInputProcessor(stage);
		
		SndPlayH = game.assets.get("Assets/SndPlayH.mp3");
		GoToLevel = game.assets.get("Assets/goToLevel.mp3");
		
		Texture BouttonJouer = game.assets.get("Assets/BTNPlay.png");
		Texture BouttonJouerHover = game.assets.get("Assets/BTNPlayH.png");
		Texture BouttonJouerClick = game.assets.get("Assets/BTNPlayC.png");
		
		ImgPlay = new Image(BouttonJouer);
		ImgPlay.setOrigin(BouttonJouer.getWidth()/2, BouttonJouer.getHeight()/2);
		ImgPlay.scaleBy(2);
		
		ImgPlayC = new Image(BouttonJouerClick);
		ImgPlayC.setOrigin(BouttonJouer.getWidth()/2, BouttonJouer.getHeight()/2);
		ImgPlayC.scaleBy(2);
		
		ImgPlayH = new Image(BouttonJouerHover);
		ImgPlayH.setOrigin(BouttonJouer.getWidth()/2, BouttonJouer.getHeight()/2);
		ImgPlayH.scaleBy(2);
		
		

		stage.addActor(ImgPlay);
		stage.addActor(ImgPlayC);
		stage.addActor(ImgPlayH);
		
	}
	
	public void dispose() {
		stage.dispose();
		//Menumusic.dispose();
	}

	public void hide() {
		
	}

	public void pause() {
		
	}

	public void render(float delta) {
		
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		stage.draw();
		
		//Play
		if(isHovered(ImgPlay, ImgPlayH, PlayisHovered)) {
			PlayisHovered = true;
		}
		else {
			PlayisHovered = false;
		}
		if(PlayisHovered && Gdx.input.isTouched()) {
			if(!Clicked) {
				GoToLevel.play();
				Clicked = true;
				
				ImgPlay.addAction(alpha(0));
				ImgPlayH.addAction(alpha(0));
				ImgPlayC.addAction(alpha(1));
				
				dispose();
				game.setScreen(new PlayScreen(game));
			}
		}
	}
	public void update(float delta) {
		stage.act(delta);
	}


	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}

	public void resume() {
		
	}

	public void show() {
		ImgPlay.setPosition(stage.getWidth()/2, stage.getHeight()/2);
		ImgPlayH.setPosition(stage.getWidth()/2, stage.getHeight()/2);
		ImgPlayC.setPosition(stage.getWidth()/2, stage.getHeight()/2);
		
		ImgPlayH.addAction(alpha(0));
		ImgPlayC.addAction(alpha(0));
	}
	public Boolean isHovered(Image Img1, Image Img2, Boolean estsurvole) {
		Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		Vector2 mouseLocalPosition = Img1.screenToLocalCoordinates(mouseScreenPosition);
		
		if(Img1.hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null && !Clicked) {
			Img1.addAction(alpha(0));
			Img2.addAction(alpha(1));
			
			if(!estsurvole && Img1.equals(ImgPlay)) {
				SndPlayH.play();
				System.out.println("Bouton play survolé");
			}
			return true;
		}
		else{
			Img1.addAction(alpha(1));
			Img2.addAction(alpha(0));

			return false;
		}
	}

}
