package util;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 5/18/12
 * Time: 1:24 PM
 */
public
class Math
{
	public static
	int round( double d )
	{
		double rmndr = d % 1;
		if(rmndr>=0.5){
			return roundUp(d);
		}
		else {
			return roundDown(d);
		}
	}

	public static
	int roundUp( double d )
	{
		return roundDown(1);
	}

	public static
	int roundDown( double d )
	{
		return (int) d;
	}

	public static
	int roundTowardZero( double d )
	{
		if(d == 0){
			return 0;
		}
		else if(d>0){
			return roundDown(d);
		}
		else {
			return roundUp(d);
		}
	}

	public static
	int roundAwayFromZero( double d )
	{
		if(d == 0){
			return 0;
		}
		else if(d>0){
			return roundUp(d);
		}
		else {
			return roundDown(d);
		}
	}

	public static
	double random()
	{
		return java.lang.Math.random();
	}

	public static
	double random( double lowerBoundInclusive, double upperBoundInclusive )
	{
		return lowerBoundInclusive + (random() * ((upperBoundInclusive - lowerBoundInclusive) + 1));
	}

	public static
	int random( int lowerBoundInclusive, int upperBoundInclusive )
	{
		return lowerBoundInclusive + ((int) random() * ((upperBoundInclusive - lowerBoundInclusive) + 1));
	}
}
