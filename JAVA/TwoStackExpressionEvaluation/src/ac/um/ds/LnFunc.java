package ac.um.ds;

public class LnFunc extends MathFunction
{
	public LnFunc()
	{
		super("ln");
	}
	@Override
	public double evaluate(double v, double dummy)
	{
		return Math.log(v);
	}
}