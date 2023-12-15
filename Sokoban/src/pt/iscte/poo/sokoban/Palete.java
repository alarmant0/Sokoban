package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Palete extends GameElement implements Movable, Blockable{

	private TypeOfAction block;
	
	public Palete(Point2D position) {
		super(position, 4);
		this.block = TypeOfAction.MOVE;
	}

	@Override
	public String getName() {
		return "Palete";
	}

	@Override
	public TypeOfAction block() {
		return this.block;
	}
	
	public void setBlock() {
		this.block = TypeOfAction.STOP;
	}

	@Override
	public TypeOfAction doSomethingWithIt(GameElement e, Direction d) {
		if (e instanceof Buraco) {
			Buraco b = (Buraco) e;
			if (b.block().ordinal() == 0) {
				b.setBlock();
				this.setBlock();
				return TypeOfAction.MOVE;
			}
		}
		if (e instanceof Blockable) {
			return TypeOfAction.opposite(((Blockable) e).block());
		}
		if (e instanceof Teleporte) {
			((Teleporte) e).teleport(this);
			return TypeOfAction.STOP;
		}
		return TypeOfAction.MOVE;
	}
	
	@Override
	public TypeOfAction canMove(Direction d) {
		Engine en = Engine.getInstance();
		Level l = en.getLevel();
		Point2D point2D = super.getPosition().plus(d.asVector());
		for(GameElement e : l.getGameElements()) {
			if (e.getPosition().equals(point2D)) {
				return doSomethingWithIt(e, d);
			}
		}
		return TypeOfAction.MOVE;
	}
	
	public TypeOfAction move(Direction d) {
		TypeOfAction t = canMove(d);
		if(t.ordinal() == 0) {
			Point2D newPosition = super.getPosition().plus(d.asVector());
			super.setPosition(newPosition);
			return TypeOfAction.MOVE;
		}
		return TypeOfAction.STOP;
	}

}
