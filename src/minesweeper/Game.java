package minesweeper;

import util.Grid;

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

	private
	Grid<Character> visible, hidden;
	private int numMines;

	public
	Game( int width, int height, int numOfMines )
	{
		visible = new Grid<Character>(width, height);
		hidden = new Grid<Character>(width, height);
		numMines = numOfMines;
		plantMines(numMines);
		assignNeighborNumbers();
	}

	/**
	 * Remove all pre-existing mines and replant new ones.
	 *
	 * @param howMany
	 */
	public
	void plantMines( int howMany )
	{
		while(numMines>0){
			for(Character c : hidden){
				if(c == MINE){
					c = SAFE;
					numMines--;
				}
			}
		}
	}

}
