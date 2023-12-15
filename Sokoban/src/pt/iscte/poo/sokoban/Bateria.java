package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Bateria extends GameElement implements Obtainable {

	public Bateria(Point2D position) {
		super(position, 3);
	}

	@Override
	public String getName() {
		return "Bateria";
	}

	@Override
	public TypeOfAction use() {
		Engine.getInstance().getBobCat().morePower();
		Engine.getInstance().remove(this);
		return TypeOfAction.MOVE;
	}

}
