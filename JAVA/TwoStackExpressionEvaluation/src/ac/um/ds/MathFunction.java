package ac.um.ds;


public abstract class MathFunction extends Operator
{
	public MathFunction(String name)
	{
		super(name, 7, Associativity.ASSOC_RIGHT, true, Token.Type.FUNCTION);
	}
}