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
	Grid( int cols, int rows )
	{
		this.width = cols;
		this.height = rows;
		elements = (T[][]) new Object[rows][cols];
	}

	public
	T get( int row, int col )
	{
		return elements[row][col];
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
	T set( int row, int col, T element )
	{
		T result = get(row, col);
		elements[row][col] = element;
		return result;
	}

	public
	Grid fill( T element )
	{
		for(int x = 0; x<cols(); x++){
			for(int y = 0; y<rows(); y++){
				set(x, y, element);
			}
		}
		return this;
	}

	/*
	 *
	 */
	public
	int getNeighboring( int row, int col, int radius, Predicate<T> filter )
	{
		int result = 0;
		for(int cRow = row - radius; cRow<=row + radius; cRow++){
			for(int cCol = col - radius; cCol<=col + radius; cCol++){
				if(cCol<0 || cCol>=cols() || cRow<0 || cRow>=rows()){
					continue;
				}
				if(!(cRow == row && cCol == col) && filter.apply(get(col, row))){
					result++;
				}
			}
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

		int cellNum = 1;
		for(int i = 0; i<test.rows(); i++){
			for(int j = 0; j<test.cols(); j++){
				test.set(i, j, cellNum++);
			}
		}

		print(test);

		Predicate<Integer> gtFive = new Predicate<Integer>()
		{
			@Override public
			boolean apply( Integer integer )
			{
				return integer>5;
			}
		};
		int neighs = test.getNeighboring(2, 2, 1, gtFive);
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