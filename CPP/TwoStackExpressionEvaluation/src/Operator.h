#pragma once
#include "Token.h"
#include <cmath>

class Operator : public Token{
public:
	enum Associativity {ASSOC_LEFT, ASSOC_RIGHT};
	const string mName;
	const int  mPrecedence;
	const Associativity  mAssociativity;
	const bool  mIsUnary;

	Operator (string name, int precedence, Associativity associativity, bool isUnitary, Token::Type type=OPERATOR)
		:Token(type), mName(name), mPrecedence(precedence), mAssociativity(associativity), mIsUnary(isUnitary){};

	virtual double evaluate (double v1, double v2) = 0;

	virtual string toText()
	{
	    return mName;
	}
};

class UnaryMinusOperator : public Operator{
public:
	// Complete the code
	UnaryMinusOperator():Operator("_",  6, ASSOC_RIGHT, true){};
	virtual double evaluate (double v1, double v2) {return -v1;};
};

class PowerOperator : public Operator{
public:
	// Complete the code
	PowerOperator():Operator("^", 5, ASSOC_RIGHT, false){};
	virtual double evaluate (double v1, double v2) {return pow(v1,v2);};
};


class MultiplicationOperator : public Operator{
public:
	// Complete the code
	MultiplicationOperator():Operator("*", 4, ASSOC_LEFT, false){};
	virtual double evaluate (double v1, double v2) {return v1*v2;};
};

class DivisionOperator : public Operator{
public:
	// Complete the code
	DivisionOperator():Operator("/", 4, ASSOC_LEFT, false){};
	virtual double evaluate (double v1, double v2) {return v1/v2;};
};

class PlusOperator : public Operator{
public:
	// Complete the code
	PlusOperator():Operator("+", 2, ASSOC_LEFT, false){};
	virtual double evaluate (double v1, double v2) {return v1+v2;};
};

class MinusOperator : public Operator{
public:
	// Complete the code
	MinusOperator():Operator("-", 2, ASSOC_LEFT, false){};
	virtual double evaluate (double v1, double v2) {return v1-v2;};
};
