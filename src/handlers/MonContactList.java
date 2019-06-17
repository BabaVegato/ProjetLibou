package handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MonContactList implements ContactListener{

	private boolean JoueurSol = false;
	private int nbContacts = 0;

	public void beginContact(Contact c) {
		Fixture fa = c.getFixtureA();
	    Fixture fb = c.getFixtureB();
	    
	    if(fa.getUserData().toString().contains("Decors") && fb.getUserData().equals("JoueurPied")) {
	    	nbContacts += 1;
	        setJoueurSol(true);
	        System.out.println("Au sol");
	    }
	    if(fb.getUserData().toString().contains("Decors") && fa.getUserData().equals("JoueurPied")) {
	    	nbContacts += 1;
	    	setJoueurSol(true);
	    	System.out.println("Au sol");
	    }
	}

	public void endContact(Contact c) {
		Fixture fa = c.getFixtureA();
	    Fixture fb = c.getFixtureB();
		if(fa.getUserData().toString().contains("Decors") && fb.getUserData().equals("JoueurPied")) {
			nbContacts -= 1;

	    }
	    if(fb.getUserData().toString().contains("Decors") && fa.getUserData().equals("JoueurPied")) {
	    	nbContacts -= 1;
	    }
	    if(nbContacts == 0){
	    	System.out.println("Hors sol");
	    	setJoueurSol(false);
	    }
	}

	public void postSolve(Contact c, ContactImpulse contImp) {
		
	}

	public void preSolve(Contact c, Manifold m) {
		
	}

	public boolean isJoueurSol() {
		return JoueurSol;
	}

	public void setJoueurSol(boolean joueurSol) {
		JoueurSol = joueurSol;
	}
	
	
}