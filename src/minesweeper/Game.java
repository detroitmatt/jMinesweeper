package minesweeper;

import ch.lambdaj.function.matcher.Predicate;
import util.Grid;
import util.Point;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 5/17/12
 * Time: 1:08 AM
 */
public
class Game
{
	public final static char MINE = 'M', SAFE = 'S';

	private Grid<Character> visible, hidden;
	private int numMines;
	private MinePlanter miner;

	public
	Game( int width, int height, int numOfMines )
	{
		visible = new Grid<Character>(width, height);
		hidden = new Grid<Character>(width, height);
		numMines = numOfMines;
		miner = new MinePlanter(numMines, numMines);
		assignNeighborNumbers();
	}

	/**
	 * Iterate over hidden and assign numbers for number of mines
	 */
	private
	void assignNeighborNumbers()
	{
		for(int x = 0; x<=hidden.cols(); x++){
			for(int y = 0; y<=hidden.rows(); y++){
				if(hidden.get(x, y) != MINE){
					int neighbors = hidden.getNeighboring(x, y, 1, new Predicate<Character>()
					{
						@Override public
						boolean apply( Character character )
						{
							return character == MINE;
						}
					});
					hidden.set(x, y, Character.forDigit(10, neighbors));
				}
			}
		}
	}

	private
	class MinePlanter
	{
		private int atLeast;
		private int noMoreThan;
		private int current;

		public
		MinePlanter( int atLeast, int noMoreThan )
		{
			setBounds(atLeast, noMoreThan);
			current = 0;
			for(Character c : hidden){
				if(c == MINE){
					current++;
				}
			}
			check();
		}

		void setBounds( int newLowerLimit, int newUpperLimit )
		{
			atLeast = newLowerLimit;
			noMoreThan = newUpperLimit;
		}

		/**
		 * Warning: This method can be expensive!
		 */
		void check()
		{
			if(!(atLeast<=current && current<=noMoreThan)){
				//Assemble list of all points in hidden.
				Vector<Point> points = new Vector<Point>(hidden.rows() + hidden.cols());
				for(int i = 0; i<hidden.rows(); i++){
					for(int ii = 0; ii<hidden.cols(); ii++){
						points.add(i + ii, new Point(i, ii));
					}
				}


				if(current<atLeast){ //We need to add mines
					Vector<Point> nonMines = new Vector<Point>(points.size() - current);
					nonMines.addAll(points);
					//Remove from points all candidates p such that hidden[p.x][p.y] == MINE
					for(int i = 0; i<points.size(); i++){ //Iterate over points
						Point p = points.get(i);
						if(hidden.get(p.x, p.y) == MINE){
							nonMines.remove(i);
						}
					}
					//nonMines is now ready to use
					while(current<atLeast){
						//Selecting points at random

					}
				}
				if(current>noMoreThan){ //we need to remove mines
					Vector<Point> mines = new Vector<Point>(points.size() - current);
					while(current>noMoreThan){ //removing mines

					}
				}
			}
		}

	}
}
