package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Caixote extends GameElement implements Movable,Blockable {

	public Caixote(Point2D point2D){
		super(point2D , 5);
	}
	
	@Override
	public String getName() {
		return "Caixote";
	}

	public TypeOfAction move(Direction d) {
		Point2D newPosition = super.getPosition().plus(d.asVector());
		TypeOfAction t = canMove(d);
		if (t.ordinal() == 0) {
			super.setPosition(newPosition);
		}		
		return t;
	}
	
	@Override
	public TypeOfAction canMove(Direction d) {
		Engine en = Engine.getInstance();
		Level l = en.getLevel();
		Point2D point2D = super.getPosition().plus(d.asVector());
		for(GameElement e : l.getGameElements()) {
			if (e.getPosition().equals(point2D)) return doSomethingWithIt(e, d);
		}
		return TypeOfAction.MOVE;
	}

	@Override
	public TypeOfAction doSomethingWithIt(GameElement e, Direction d) {
		Engine en = Engine.getInstance();
		if (e instanceof Alvo) {
			if(en.getLevel().checkLevelComplete((Alvo)e)) {
				int level = en.getLevelInt();
				Level.saveScore();
				en.setLevel(level+1, Level.createLevel(level+1));
			}
		}
		if (e instanceof Blockable) return TypeOfAction.opposite(((Blockable) e).block());
		if (e instanceof Teleporte) return ((Teleporte) e).teleport(this);
		return TypeOfAction.MOVE;
	}
		
	@Override
	public TypeOfAction block() { // Em blockables MOVE = block, STOP = Nao block
		return TypeOfAction.MOVE;
	}
	
}