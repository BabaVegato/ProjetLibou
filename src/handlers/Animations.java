package handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animations
{
  private TextureRegion[] frames;
  private float time;
  private float delay;
  private int currentFrame;
  
  public Animations(TextureRegion[] frames, float delay) {
    setFrames(frames, delay);
  }
  
  public Animations(TextureRegion[] frames) {
    this(frames, 0.0F);
  }
  
  public void setFrames(TextureRegion[] frames, float delay) {
    this.frames = frames;
    this.delay = delay;
    time = 0.0F;
    currentFrame = 0;
  }
  
  public void update(float dt) { time += dt;
    while (time >= delay)
      step();
  }
  
  public void step() {
    time -= delay;
    currentFrame += 1;
    if (currentFrame == frames.length)
      currentFrame = 0;
  }
  
  public TextureRegion getFrame() {
    return frames[currentFrame];
  }
}