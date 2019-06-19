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
    setCurrentFrame(0);
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
		  setCurrentFrame(getCurrentFrame() + 1);
		  if (getCurrentFrame() == frames.length){
			  setCurrentFrame(0);
			  setFini(true);
		  }
		  else{
			  setFini(false);
		  }
	  }
	  if(!unique){
		  time -= delay;
		  setCurrentFrame(getCurrentFrame() + 1);
		  if (getCurrentFrame() == frames.length){
			  setCurrentFrame(0);
		  }
	  }
	  
  }
  
  public TextureRegion getFrame() {
    return frames[getCurrentFrame()];
  }

public boolean isFini() {
	return fini;
}

public void setFini(boolean fini) {
	this.fini = fini;
}

public int getCurrentFrame() {
	return currentFrame;
}

public void setCurrentFrame(int currentFrame) {
	this.currentFrame = currentFrame;
}
}