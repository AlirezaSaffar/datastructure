#pragma once
#include "Token.h"

class MathFunction : public Operator{
public:
	// Complete the code
	MathFunction(const string& name): Operator(name,  7, Operator::ASSOC_LEFT, true, FUNCTION){}
};


class  SinFunc: public MathFunction{
public:
	SinFunc(): MathFunction ("sin"){};
	virtual double evaluate (double v, double dummy) {return sin(v);};
};


class  CosFunc: public MathFunction{
public:
	CosFunc(): MathFunction ("cos"){};
	virtual double evaluate (double v, double dummy) {return cos(v);};
};


class  ExpFunc: public MathFunction{
public:
	ExpFunc(): MathFunction ("exp"){};
	virtual double evaluate (double v, double dummy) {return exp(v);};
};


class  LnFunc: public MathFunction{
public:
	LnFunc(): MathFunction ("ln"){};
	virtual double evaluate (double v, double dummy) {return log(v);};
};
