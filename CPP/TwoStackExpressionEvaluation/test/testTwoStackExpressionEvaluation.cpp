#include "../src/ExpressionEvaluator.cpp"

#include <iostream>
#include <math.h>

using namespace std;
void postfix();
void evalutaionTest();

int main()
{
	cout << "=============================================" << endl;
	cout << "Test 1. Postfix" << endl;
	cout << "=============================================" << endl;
	postfix();
	cout << "\n\n";
	cout << "=============================================" << endl;
	cout << "Test 2. Evaluation" << endl;
	cout << "=============================================" << endl;
	evalutaionTest();

	return 0;
}

void postfix()
{
	unsigned int i;
	ExpressionEvaluator ee;
	vector<Token *> vtInfix, vtPostfix;
	string tokenizedPostfixString;
	string testCase[] = {"(-sin(-x))^(-10-x)", "(z-x)-(y-(z-1)^(4-x))^(y-z)", "sin(cos(x+exp(x-z))+ln(x))", "(7-x-5)/z/y*z*x+7+8-9", "7^z^y^2/z^x^3"};
	string tokenized[] = {" x _ sin _ 10 _ x - ^", " z x - y z 1 - 4 x - ^ - y z - ^ -", " x x z - exp + cos x ln + sin", " 7 x - 5 - z / y / z * x * 7 + 8 + 9 -", " 7 z y 2 ^ ^ ^ z x 3 ^ ^ /"};
	for (i = 0; i < 5; i++)
	{
		ee.tokenizeString(testCase[i], vtInfix);
		ee.infix2Postfix(vtInfix, vtPostfix);
		tokenizedPostfixString = ee.tokenSeq2String(vtPostfix);
		cout << "Expression: " << testCase[i] << endl
			 << "Evaluated Postfix: " << tokenizedPostfixString << endl
			 << "Correct Answer: " << tokenized[i] << endl;
		if (tokenizedPostfixString == tokenized[i])
			cout << "True" << endl
				 << endl;
		else
		{
			cout << "False" << endl
				 << endl;
			cout << "\n";
			throw runtime_error("Incorrect postfix function.");
		}
		vtInfix.clear();
		vtPostfix.clear();
	}
}

void evalutaionTest()
{
	unsigned int j;
	ExpressionEvaluator ee;
	double evaluatedResult;
	map<string, double> vv;
	vv["x"] = 1;
	vv["y"] = 2;
	vv["z"] = 3;
	string testCase[] = {"(-sin(-x))^(-10-x)", "(z-x)-(y-(z-1)^(4-x))^(y-z)", "sin(cos(x+exp(x-z))+ln(x))", "(7-x-5)/z/y*z*x+7+8-9", "7^z^y^2/6^z^4^x"};
	double result[] = {6.67, 2.16, 0.40, 6.50, 264661};
	int res = 0;
	for (j = 0; j < 5; j++)
	{
		evaluatedResult = ee.evaluateExpression(testCase[j], vv);
		cout << "Evaluated: " << evaluatedResult << endl
			 << "Answer: " << result[j] << endl;

		if (fabs(evaluatedResult - result[j]) < 0.01)
			res += 20;
		else
		{
			cout << "\n";
			throw runtime_error("Incorrect evaluation function.");
		}
		cout << "Score: " << res << endl
			 << endl;
	}
}