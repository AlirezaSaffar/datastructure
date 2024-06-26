package ac.um.ds;

public class CosFunc extends MathFunction
{
	public CosFunc()
	{
		super("cos");
	}
	@Override
	public double evaluate(double v, double dummy)
	{
		return Math.cos(v);
	}
}