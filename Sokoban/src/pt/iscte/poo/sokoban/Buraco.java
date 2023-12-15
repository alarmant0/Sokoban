package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Buraco extends GameElement implements Blockable {

	private TypeOfAction block = TypeOfAction.MOVE; // Em blockables MOVE = block, STOP = Nao block
	
	public Buraco(Point2D position) {
		super(position, 2);
	}

	@Override
	public String getName() {
		return "Buraco";
	}

	public void setBlock() {
		this.block = TypeOfAction.STOP;
	}
	
	@Override
	public TypeOfAction block() {
		return this.block;
	}

}
