package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Vazio extends GameElement {

	public Vazio(Point2D position) {
		super(position, 1);
	}

	@Override
	public String getName() {
		return "Vazio";
	}
	
	@Override
	public String toString() {
		return this.getName() + " : " + super.getPosition();
	}

}
