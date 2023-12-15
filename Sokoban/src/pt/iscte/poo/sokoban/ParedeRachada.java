package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class ParedeRachada extends GameElement implements Blockable {

	public ParedeRachada(Point2D position) {
		super(position, 2);
	}

	@Override
	public String getName() {
		return "ParedeRachada";
	}

	@Override
	public TypeOfAction block() {
		Engine en = Engine.getInstance();
		if (en.getBobCat().hasMartelo()) en.remove(this);
		return TypeOfAction.MOVE;
	}

}
