package dynamiques;

import com.badlogic.gdx.physics.box2d.World;

import states.PlayScreen;

public abstract class Ennemi extends Personnage{

	public Ennemi(PlayScreen screen, World monde, int PosX, int PosY) {
		super(screen, monde, PosX, PosY);
	}

}
