package util;

import com.google.common.base.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 5/17/12
 * Time: 12:38 AM
 */
public
class Grid<T>
{
	private
	T[][] elements;

	public
	Grid( int width, int height )
	{
		elements = (T[][]) new Object[width][height];
	}

	public
	T get( int x, int y )
	{
		return elements[x][y];
	}

	public
	T set( int x, int y, T element )
	{
		T result = get(x, y);
		elements[x][y] = element;
		return result;
	}

	public
	int getNeighboring( int x, int y, int radius, Predicate<T> filter )
	{
		int result = 0;
		for(int col = x - radius; col<=x + radius; col++){
			for(int row = y + radius; col<=y - radius; row++){
				if(filter.apply(get(col, row))){
					result++;
				}
			}
		}
		return result;
	}
}