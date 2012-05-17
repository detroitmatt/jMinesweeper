package util;

import com.google.common.base.Predicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 5/17/12
 * Time: 12:38 AM
 */
public
class Grid<T> implements Iterable<T>
{
	private T[][] elements;
	private int width, height;

	public
	Grid( int width, int height )
	{
		this.width = width;
		this.height = height;
		elements = (T[][]) new Object[width][height];
	}

	public
	T get( int x, int y )
	{
		return elements[x][y];
	}

	public
	int rows()
	{
		return height;
	}

	public
	int cols()
	{
		return width;
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

	@Override
	public
	Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			int curX = 0;
			int curY = 0;

			@Override public
			boolean hasNext()
			{
				return (curX>=width) && (curY>=height);
			}

			@Override public
			T next()
			{
				if(!hasNext()){
					throw new NoSuchElementException();
				}

				T result = get(curX, curY);
				if(++curX>width){
					curX = 0;
					curY++;
				}
				return result;
			}

			@Override public
			void remove()
			{
				throw new UnsupportedOperationException();
			}
		}
	}
}