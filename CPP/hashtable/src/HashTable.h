#pragma once
#include <iostream>
#include <cstring>
#include "Map.h"
using namespace std;


template <class K, class V>
class HashTable : public Map<K, V>
{
public:
	HashTable(int (*hashFunc)(K), int (*hashFunc2)(K), float maxLoadingFactor = 0.7, int initCapacity = 7)
	{
		// Write your code here
		this->ch=true;
		this->mSize=0;
		this->hashFunc=hashFunc;
		this->hashFunc2=hashFunc2;
		this->mCapacity=initCapacity;
		this->mMaxLoadingFactor=maxLoadingFactor;
    	this->mStateTable = new bool[initCapacity]();
        int i;
		mTable = new std::pair<K, V>[mCapacity];
	}

	virtual ~HashTable()
	{
		delete[] mTable;
		delete[] mStateTable;
	}

	
	 bool isaval(int x){
		int i;

		bool check=true;
		for(i=2;i<x/4+1;i++){if(x%i==0){check=false;}}

		return check;
	}
	



	virtual void assign(const K &key, const V &value)
	{
		// Write your code here
			int x,y;
		int i;
		int check=0;
		i=0;

		while(this->mStateTable[((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity]){
			y=((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity;
			if(this->mTable[y].first==key){
				check=1;
				this->mTable[y].second=value;
                break;
			}
			i++;
		}
		for(i=0;;i++){
			if(check==1) break;
			x= (*hashFunc)(key) + (*hashFunc2)(key)*i;
			y=x%this->mCapacity;

			if(!this->mStateTable[y]&&check==0){
			if(ch)	this->mSize++;
			
				this->mTable[y].first=key;
				this->mTable[y].second=value;
				this->mStateTable[y]=true;
		        break;
			}
		}
		float me =(float) this->mSize / (float)this->mCapacity;
	    if(me>this->mMaxLoadingFactor){
			std::pair<K, V> *oldk = this->mTable;
			 bool *olds=this->mStateTable;
			 i=this->mCapacity*2;
			 while(true){
				 if(this->isaval(i)==true){break;}
				 i++;
			 }
			 int oldcp=this->mCapacity;
			 this->mCapacity=i;
			 this->mStateTable = new bool[this->mCapacity]();
		=    mTable = new std::pair<K, V>[mCapacity];

				for(i=0;i<oldcp;i++){
					this->ch=false;
				     if(olds[i]){this->assign(oldk[i].first, oldk[i].second);}
					this->ch=true;
				}
		
		delete[] oldk;
		delete[] olds;

		}

	}

	virtual void remove(const K &key)
	{
		// Write your code here
		int i,j,y;
		V vv;
		K kk;
		y=0;
		i=0;
		while(this->mStateTable[((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity]){
			y=((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity;
			if(this->mTable[y].first==key){
                this->mStateTable[y]=false;
				this->mSize--;


				break;
			}
			i++;

		}
		i=1;
		while(this->mStateTable[(y+i*(*hashFunc2)(key))%this->mCapacity]){
			j=(y+i*(*hashFunc2)(key))%this->mCapacity;
			vv=this->mTable[j].second;
			kk=this->mTable[j].first;
			this->mStateTable[j]=false;
			this->mSize--;
			this->assign(kk, vv);
			i++;
		}
	}
	virtual int size()
	{
		return mSize;
	}

	virtual int capacity()
	{
		return mCapacity;
	}

	virtual bool hasKey(const K &key)
	{
		// Write your code here
		bool ans=false;
		int i,y;
		i=0;
		while(this->mStateTable[((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity]){
			y=((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity;
			if(this->mTable[y].first==key){
				ans= true;
				break;
			}
			i++;
		}

		return ans;
	}

	virtual V &operator[](const K &key)
	{
		// Write your code here
		int i,y;
		V ans=NULL;
		i=0;
		while(this->mStateTable[((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity]){
			y=((*hashFunc)(key)+(*hashFunc2)(key)*i)%this->mCapacity;
			if(this->mTable[y].first==key){
				ans= this->mTable[y].second;
				break;
			}
			i++;
		}
		return ans;
	}

	void print()
	{
		for (int i = 0; i < mCapacity; i++)
			cout << i << "\t";
		cout << endl;

		for (int i = 0; i < mCapacity; i++)
		{
			if (mStateTable[i])
				cout << mTable[i].second << "\t";
			else
				cout << "*"
					 << "\t";
		}
		cout << endl;
	}

	string toString()
	{
		string s = "";
		s += "size:" + to_string(this->mSize) + "\n";
		for (int i = 0; i < this->mCapacity - 1; i++)
		{
			if (this->mStateTable[i])
				s += to_string((this->mTable[i]).first) + "," + to_string((this->mTable[i]).second) + ", ";
			else
				s += "-1, ";
		}
		if (this->mStateTable[this->mCapacity - 1])
			s += to_string(this->mTable[this->mCapacity - 1].first) + "," + to_string((this->mTable[this->mCapacity - 1]).second);
		else
			s += "-1";
		return s;
	}

protected:
    bool ch;
	int mCapacity;
	int mSize;
	std::pair<K, V> *mTable;
	bool *mStateTable; // 0 => empty ,  1 => full
	int (*hashFunc)(K key);
	int (*hashFunc2)(K key);
	float mMaxLoadingFactor;
};
