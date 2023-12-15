package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Parede extends GameElement implements Blockable {

	public Parede(Point2D position) {
		super(position, 2);
		
	}

	@Override
	public String getName() {
		return "Parede";
	}

	@Override
	public String toString() {
		return this.getName() + " : " + super.getPosition();
	}

	@Override
	public TypeOfAction block() {
		return TypeOfAction.MOVE;
	}
	
}
