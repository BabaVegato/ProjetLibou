package handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MonContactList implements ContactListener{

	private boolean JoueurSol = false;
	private int nbContacts = 0;
	private String IDEnnemi;
	private String IDPic;
	private boolean DegatsAGerer = false;
	private boolean PicActive = false;
	private boolean TirGunMur = false;
	private String IDTir;

	public void beginContact(Contact c) {
		Fixture fa = c.getFixtureA();
	    Fixture fb = c.getFixtureB();
	    
	    /////////// SOL - JOUEUR /////////////
	    if((fa.getUserData().toString().contains("Decors") || fa.getUserData().toString().contains("Pic")) && fb.getUserData().equals("JoueurPied")) {
	    	nbContacts += 1;
	        setJoueurSol(true);
	    }
	    if((fb.getUserData().toString().contains("Decors") || fb.getUserData().toString().contains("Pic")) && fa.getUserData().equals("JoueurPied")) {
	    	nbContacts += 1;
	    	setJoueurSol(true);
	    }
	    
	    /////////// EPEE - ENNEMI /////////////
	    if(fa.getUserData().toString().contains("Ennemi") && fb.getUserData().toString().contains("Epee")) {
	    	DegatsAGerer = true;
	        setIDEnnemi(fa.getUserData().toString());
	    }
	    if(fb.getUserData().toString().contains("Ennemi") && fa.getUserData().toString().contains("Epee")) {
	    	DegatsAGerer = true;
	    	setIDEnnemi(fb.getUserData().toString());
	    }
	    /////////// Truc - Pic ////////////////
	    if(fa.getUserData().toString().contains("Pic") && fb.getUserData().toString().contains("Joueur")) {
	    	setPicActive(true);
	        setIDPic(fa.getUserData().toString());
	    }
	    if(fb.getUserData().toString().contains("Pic") && fa.getUserData().toString().contains("Joueur")) {
	    	setPicActive(true);
	    	setIDPic(fb.getUserData().toString());
	    }
	    
	    /////////// TirGun - Decors ////////////////
	    if((fa.getUserData().toString().contains("Decors") || fa.getUserData().toString().contains("Pic")) && fb.getUserData().toString().contains("TirGun")) {
	    	setTirGunMur(true);
	        setIDTir(fb.getUserData().toString());
	    }
	    if((fb.getUserData().toString().contains("Decors") || fb.getUserData().toString().contains("Pic")) && fa.getUserData().toString().contains("TirGun")) {
	    	setTirGunMur(true);
	    	setIDTir(fa.getUserData().toString());
	    }
	}

	public void endContact(Contact c) {
		Fixture fa = c.getFixtureA();
	    Fixture fb = c.getFixtureB();
	    
	    /////////// SOL - JOUEUR /////////////
		if((fa.getUserData().toString().contains("Decors")|| fa.getUserData().toString().contains("Pic")) && fb.getUserData().equals("JoueurPied")) {
			nbContacts -= 1;

	    }
	    if((fb.getUserData().toString().contains("Decors")|| fb.getUserData().toString().contains("Pic")) && fa.getUserData().equals("JoueurPied")) {
	    	nbContacts -= 1;
	    }
	    if(nbContacts == 0){
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

	public String getIDEnnemi() {
		return IDEnnemi;
	}

	public void setIDEnnemi(String iDEnnemi) {
		IDEnnemi = iDEnnemi;
	}

	public boolean isDegatsAGerer() {
		return DegatsAGerer;
	}

	public void setDegatsAGerer(boolean degatsAGerer) {
		DegatsAGerer = degatsAGerer;
	}

	public boolean isPicActive() {
		return PicActive;
	}

	public void setPicActive(boolean picActive) {
		PicActive = picActive;
	}

	public String getIDPic() {
		return IDPic;
	}

	public void setIDPic(String iDPic) {
		IDPic = iDPic;
	}

	public boolean isTirGunMur() {
		return TirGunMur;
	}

	public void setTirGunMur(boolean tirGunMur) {
		TirGunMur = tirGunMur;
	}

	public String getIDTir() {
		return IDTir;
	}

	public void setIDTir(String iDTir) {
		IDTir = iDTir;
	}
	
	
}