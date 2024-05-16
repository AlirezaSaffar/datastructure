#pragma once

class X
{
private:
	int value;

public:
	X(const X &x);
	X(int val);
	X();
	~X();

	bool operator>(const X &right) const;
	bool operator<(const X &right) const;
	bool operator>=(const X &right) const;
	bool operator<=(const X &right) const;
	bool operator==(const X &right) const;
	bool operator!=(const X &right) const;
	X &operator=(const X &right);

	int getValue() const;
};
