package handlers;

import com.badlogic.gdx.graphics.Texture;

public class Animations
{
  private Texture[] frames;
  private float time;
  private float delay;
  private int currentFrame;
  private boolean unique;
  private boolean fini = false;
  
  public Animations(TextureRegion[] frames, boolean unique, float delay) {
	  this.unique = unique;
	  setFrames(frames, delay);
  public Animations(Texture[] frames, float delay) {
    setFrames(frames, delay);
  }
  
  public Animations(Texture[] frames) {
    this(frames, 0.0F);
  }
  
  public void setFrames(Texture[] frames, float delay) {
    this.frames = frames;
    this.delay = delay;
    time = 0.0F;
    currentFrame = 0;
  }
  
  public void update(float dt) { 
	  if(unique){
		  if(!fini){
			  time += dt;
			  while (time >= delay)
				  step();
		  }	  
	  }
	  
	  if(!unique){
		  time += dt;
		  while (time >= delay)
	      step();
	  }
  }
  
  public void step() {
    time -= delay;
    currentFrame += 1;
    if (currentFrame == frames.length){
    	currentFrame = 0;
    	fini = true;
    }
    else{
    	fini = false;
    }
  }
  
  public Texture getFrame() {
    return frames[currentFrame];
  }
}