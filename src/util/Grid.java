package util;

import ch.lambdaj.function.matcher.Predicate;

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
	Grid fill( T element )
	{
		for(int i = 0; i<elements.length; i++){
			T[] ts = elements[i];
			for(int j = 0; j<ts.length; j++){
				set(i, j, element);
			}
		}
		return this;
	}

	public
	int getNeighboring( int x, int y, int radius, Predicate<T> filter )
	{
		int result = 0;
		for(int col = x - radius; col<=x + radius; col++){
			for(int row = y + radius; row>=y - radius; row--){
				try {
					if((row != y && col != x) && filter.apply(get(col, row))){
						result++;
						System.out.println("Match: " + get(row, col));
					}
					else {
						System.out.println("No match: " + get(row, col));
					}
				} catch(IndexOutOfBoundsException ex){
					System.err.println("Out of bounds");
				}
				System.out.println("row--");
			}
			System.out.println("col++");
		}
		return result;
	}

	@Override public
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
		};
	}

	public static
	void main( String[] args )
	{
		final Grid<Integer> test = new Grid<Integer>(3, 3);
		test.fill(1);
		print(test);

		int cellNum = 1;
		for(int i = 0; i<test.rows(); i++){
			for(int j = 0; j<test.cols(); j++){
				test.set(i, j, cellNum++);
			}
		}
		print(test);

		System.out.println("Neighbors:\n");
		int neighs = test.getNeighboring(2, 2, 1, new Predicate<Integer>()
		{
			@Override public
			boolean apply( Integer integer )
			{
				return integer>5;
			}
		});
		System.out.println(neighs);
		System.out.println("Done.");
	}

	public static
	void print( Grid g )
	{
		for(int i = 0; i<g.rows(); i++){
			for(int j = 0; j<g.cols(); j++){
				System.out.print("[" + g.get(i, j) + "]");
			}
			System.out.println();
		}
	}
}