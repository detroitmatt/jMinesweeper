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
		elements = (T[][]) new Object[cols][rows];
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

	/*
	 *
	 */
	public
	int getNeighboring( int x, int y, int radius, Predicate<T> filter )
	{
		int result = 0;
		/*
		 * Start at the top row, go to the bottom row. Row decreases.
		 */
		for(int row = y + radius; row>=y - radius; row--){
			/*
			 * Start at the leftmost col, go to the rightmost col. Col increases.
			 */
			for(int col = x - radius; col<=x + radius; col++){
				System.out.println("[" + x + "," + y + "]");

				if(col<0 || col>=cols() || row<0 || row>=rows()){
					continue;
				}

				if(!(row == y || col == x)){
					if(filter.apply(get(col, row))){
						result++;
					}
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

		for(int i = 0; i<test.rows(); i++){
			for(int ii = 0; ii<test.cols(); ii++){
				System.out.println(test.get(ii, i));
			}
		}

		System.out.println("Neighbors:\n");
		Predicate<Integer> gtFive = new Predicate<Integer>()
		{
			@Override public
			boolean apply( Integer integer )
			{
				return integer>5;
			}
		};
		System.out.println("6=" + gtFive.apply(6));
		int neighs = test.getNeighboring(2, 2, 1, gtFive);
		System.out.println("Doing it manually:");
		int result = 0, x = 2, y = 2, radius = 1;

//		for(int row = y + radius; row<=y - radius; row--){
//			/*
//			 * Start at the leftmost col, go to the rightmost col. Col increases.
//			 */
//			for(int col = x - radius; col<=x + radius; x++){
//				try {
//					System.out.println("[" + x + "," + y + "]");
//					System.out.println(test.get(col, row) + " matched " + gtFive.toString() + " ?");
//					System.out.println(gtFive.apply(test.get(col, row)));
//
//					if(!(row == y || col == x)){
//						if(gtFive.apply(test.get(col, row))){
//							result++;
//						}
//					}
//				} catch(IndexOutOfBoundsException ex){
//					System.err.println("Out of bounds: " + x + ", " + y);
//				}
//			}
//		}
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