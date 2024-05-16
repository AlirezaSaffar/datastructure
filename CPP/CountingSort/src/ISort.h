#pragma once

template <class T>
class ISort
{
public:
	virtual void sort (T* data, int n)=0;
};
