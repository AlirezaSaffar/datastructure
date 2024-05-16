#include "X.h"

X::X(const X &x)
{
	this->value = x.value;
}
X::X(int val)
{
	this->value = val;
}
X::X() {}
X::~X() {}

X &X::operator=(const X &right)
{
	this->value = right.value;
	return (*this);
}

bool X::operator>(const X &right) const
{
	return (value > right.value);
}

bool X::operator<(const X &right) const
{
	return (value < right.value);
}

bool X::operator>=(const X &right) const
{
	return (value >= right.value);
}

bool X::operator<=(const X &right) const
{
	return (value <= right.value);
}

bool X::operator==(const X &right) const
{
	return (value == right.value);
}

bool X::operator!=(const X &right) const
{
	return (value != right.value);
}

int X::getValue() const
{
	return this->value;
}
