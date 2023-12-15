package pt.iscte.poo.sokoban;

import java.util.Comparator;

public class ComparatorDeGES implements Comparator<GameElement> {

	@Override
	public int compare(GameElement o1, GameElement o2) {
		return o2.getLayer() - o1.getLayer();
	}

}
