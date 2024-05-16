#pragma once

template <class K, class V>
class Map
{
public:
	Map(){};
	virtual ~Map(){};

	virtual void assign(const K &key, const V &value) = 0;
	virtual void remove(const K &key) = 0;
	virtual V &operator[](const K &key) = 0;
	virtual int size() = 0;
	virtual bool hasKey(const K &key) = 0;
};
