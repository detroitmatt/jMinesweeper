package util;

import java.io.PrintStream;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 5/22/12
 * Time: 1:13 PM
 */
public
class IO
{
	public static
	void print( String s )
	{
		System.out.print(s);
	}

	public static
	void println( String s )
	{
		System.out.println(s);
	}

	public static
	void print( int s )
	{
		System.out.print(s);
	}

	public static
	void println( int s )
	{
		System.out.println(s);
	}

	public static
	void print( double s )
	{
		System.out.print(s);
	}

	public static
	void println( double s )
	{
		System.out.println(s);
	}

	public static
	void print( Object s )
	{
		System.out.print(s);
	}

	public static
	void println( Object s )
	{
		System.out.println(s);
	}

	public static
	void println()
	{
		System.out.println();
	}

	public static
	PrintStream branch( final PrintStream trunk, final PrintStream branch )
	{
		return new PrintStream(trunk)
		{
			boolean error = false;

			@Override public
			boolean checkError()
			{return trunk.checkError() && branch.checkError() && error;}

			@Override public
			void close()
			{
				trunk.close(); branch.close();
			}

			@Override public
			void flush()
			{
				trunk.flush(); branch.flush();
			}

			@Override public
			void print( boolean b )
			{
				trunk.print(b);
				branch.print(b);
			}

			@Override public
			void print( char c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void print( char[] c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void print( double c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void print( long c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void print( float c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void print( int c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void print( Object c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void print( String c )
			{
				trunk.print(c);
				branch.print(c);
			}

			@Override public
			void println( boolean b )
			{
				trunk.println(b);
				branch.println(b);
			}

			@Override public
			void println( char c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override public
			void println( char[] c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override public
			void println( double c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override public
			void println( long c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override public
			void println( float c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override public
			void println( int c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override public
			void println( Object c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override public
			void println( String c )
			{
				trunk.println(c);
				branch.println(c);
			}

			@Override protected
			void setError()
			{
				error = true;
			}
		};
	}
}
