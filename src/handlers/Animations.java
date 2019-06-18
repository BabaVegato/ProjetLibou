package handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animations
{
  private TextureRegion[] frames;
  private float time;
  private float delay;
  private int currentFrame;
  private boolean unique;
  private boolean fini = false;
  
  public Animations(TextureRegion[] frames, boolean unique, float delay) {
	  this.unique = unique;
	  setFrames(frames, delay);
  }
  
  public void setFrames(TextureRegion[] frames, float delay) {
    this.frames = frames;
    this.delay = delay;
    time = 0.0F;
    currentFrame = 0;
  }
  
  public void update(float dt) { 
	  if(!isFini()){
		  time += dt;
		  while (time >= delay)
	      step();
	  }
  }
  
  public void step() {
	  if(unique){
		  time -= delay;
		  currentFrame += 1;
		  if (currentFrame == frames.length){
			  currentFrame = 0;
			  setFini(true);
		  }
		  else{
			  setFini(false);
		  }
	  }
	  if(!unique){
		  time -= delay;
		  currentFrame += 1;
		  if (currentFrame == frames.length){
			  currentFrame = 0;
		  }
	  }
	  
  }
  
  public TextureRegion getFrame() {
    return frames[currentFrame];
  }

public boolean isFini() {
	return fini;
}

public void setFini(boolean fini) {
	this.fini = fini;
}
}