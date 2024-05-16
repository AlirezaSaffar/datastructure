package ac.um.ds;

public class PowerOperator extends Operator
{
	public PowerOperator()
	{
		super("^", 5, Associativity.ASSOC_RIGHT, false);
	}
	@Override
	public double evaluate(double v1, double v2)
	{
		return Math.pow(v1,v2);
	}
}


