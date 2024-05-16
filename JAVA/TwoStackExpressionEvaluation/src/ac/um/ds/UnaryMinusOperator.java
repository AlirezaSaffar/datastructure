package ac.um.ds;

public class UnaryMinusOperator extends Operator
{
	public UnaryMinusOperator()
	{
		super("_", 6, Associativity.ASSOC_RIGHT , true);
	}
	@Override
	public double evaluate(double v1, double v2)
	{
		return -v1;
	}
}