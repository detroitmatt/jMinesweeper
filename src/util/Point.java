package util;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 5/17/12
 * Time: 5:56 PM
 */
public
class Point
{
	public int x, y;

	public
	Point( int x, int y )
	{
		this.x = x; this.y = y;
	}

	@Override public
	boolean equals( Object o )
	{
		if(this == o){
			return true;
		}
		if(o == null || getClass() != o.getClass()){
			return false;
		}

		Point point = (Point) o;
		return (x != point.x) && (y != point.y);
	}

	@Override public
	int hashCode()
	{
		return 31 * (x + y);
	}
}
