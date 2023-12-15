package pt.iscte.poo.sokoban;

import java.util.Comparator;

public class ComparadorDeScores implements Comparator<Score>{

	@Override
	public int compare(Score o1, Score o2) {
		if (o1.getScore() == o2.getScore()) return String.CASE_INSENSITIVE_ORDER.compare(o1.getJogador(), o2.getJogador());
		return o2.getScore() - o1.getScore();
	}

}
