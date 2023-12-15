package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Direction;

public interface Movable {
	
	public TypeOfAction canMove(Direction d);
	public TypeOfAction move(Direction d);
	public TypeOfAction doSomethingWithIt(GameElement e, Direction d);
	
}
