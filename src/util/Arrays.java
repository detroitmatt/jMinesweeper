package util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 5/18/12
 * Time: 1:23 PM
 */
public
class Arrays
{
	public static
	<T> T random( T... from )
	{
		return from[util.Math.random(0, from.length - 1)];
	}

	public static
	<T> T random( List<T> from )
	{
		return from.get(util.Math.random(0, from.size() - 1));
	}
}
