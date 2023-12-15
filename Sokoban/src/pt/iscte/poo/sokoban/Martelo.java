package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Martelo extends GameElement implements Obtainable {

	public Martelo(Point2D position) {
		super(position, 3);
	}

	@Override
	public String getName() {
		return "Martelo";
	}

	@Override
	public TypeOfAction use() {
		Engine.getInstance().getBobCat().setMartelo();
		Engine.getInstance().remove(this);
		return TypeOfAction.MOVE;
	}

}
