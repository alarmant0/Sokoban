package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Alvo extends GameElement {
	
	public Alvo(Point2D position) {
		super(position, 1);
	}

	//GETTERS
	
	@Override
	public String getName() {
		return "Alvo";
	}
	
	//-----------------------
	
	@Override
	public String toString() {
		return this.getName() + " : " + super.getPosition();
	}

}
