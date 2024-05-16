package ac.um.ds;

public class MultiplicationOperator extends Operator
{
	public MultiplicationOperator()
	{
		super("*", 4, Associativity.ASSOC_LEFT, false);
	}
	@Override
	public double evaluate(double v1, double v2)
	{
		return v1 * v2;
	}
}