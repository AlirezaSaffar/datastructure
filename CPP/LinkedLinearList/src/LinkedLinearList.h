#pragma once

template <class T>
class LinkedLinearList{
private:
	class Node{
	public:
		T	mData;
		Node*	mPrev;
		Node*	mNext;
	};

public:
	class Iterator{
	public:
		//constructors:
		Iterator ()
			:mCurNode (NULL){}

		Iterator (const Iterator &other)
			:mCurNode (other.mCurNode){}

		Iterator (Node* node)
			:mCurNode (node){}

		//overloading operators:
		virtual Iterator& operator++(){ // preincrement
			// Write your code here
			this->mCurNode=this->mCurNode->mNext;
			return *this;
		}

		virtual Iterator operator++(int dummy){ // postincrement
			// Write your code here
			Iterator n=*this;
			this->mCurNode=this->mCurNode->mNext;
			return n;
		}

		virtual Iterator& operator--(){ // predecrement
			// Write your code here
			this->mCurNode=this->mCurNode->mPrev;
			return *this;
		}

		virtual Iterator operator--(int dummy){ // postdecrement
			// Write your code here
			Iterator n=*this;
			this->mCurNode=this->mCurNode->mPrev;
			return n;

		}

		bool operator!=(const Iterator& right)const {
			// Write your code here
			if(right.mCurNode==this->mCurNode){return false;}else{return true;}
		}

		bool operator==(const Iterator& right)const {
			// Write your code here
			if(right.mCurNode!=this->mCurNode){return false;}else{return true;}
		}

		T* operator->() const {
			// Write your code here
			return &(this->mCurNode->mData);
		}

		T& operator*() const {
			// Write your code here
			return (this->mCurNode->mData);

		}

		friend class LinkedLinearList;

	private:
		Node *mCurNode;
	};

	LinkedLinearList(void){
		mSize = 0;
		mHeaderNode = new Node();
		mHeaderNode->mNext = mHeaderNode;
		mHeaderNode->mPrev = mHeaderNode;
	}

	virtual ~LinkedLinearList(void) {
		Node *n, *nn;
		for (n = mHeaderNode->mNext; n != mHeaderNode;){
			nn = n->mNext;
			delete n;
			n = nn;
		}
		delete mHeaderNode;
	}

public:

	virtual Iterator begin() const {
		// Write your code here
		Iterator* ans = new Iterator(this->mHeaderNode->mNext);
		return *ans;
	}

	virtual Iterator end() const {
		// Write your code here
		Iterator* ans = new Iterator(this->mHeaderNode);
		return *ans;
	}

	virtual Iterator rbegin() const {
		// Write your code here
		Iterator* ans = new Iterator(this->mHeaderNode->mPrev);
		return *ans;
	}

	virtual Iterator rend() const {
		// Write your code here
		Iterator* ans = new Iterator(this->mHeaderNode);
		return *ans;
	}


	virtual Iterator insert (Iterator it, const T& data) {
		// Write your code here
		this->mSize++;
        Node* newnode= new Node();
        newnode->mData=data;
        newnode->mNext=it.mCurNode;
        newnode->mPrev=it.mCurNode->mPrev;
        (it.mCurNode->mPrev)->mNext=newnode;
        it.mCurNode->mPrev=newnode;
        it.mCurNode=it.mCurNode->mPrev;
        return it;

	}


	virtual Iterator remove (Iterator it){
		// Write your code here
this->mSize--;
Node* del = it.mCurNode;
		it.mCurNode=it.mCurNode->mNext;
		it.mCurNode->mPrev->mPrev->mNext=it.mCurNode;
		it.mCurNode->mPrev=it.mCurNode->mPrev->mPrev;
		delete del;
		return it;

	}

	virtual int size() const {
		return mSize;
	};

private:
	Node* mHeaderNode;
	int mSize;
};

template <class T>
std::ostream& operator<<(std::ostream& os, const LinkedLinearList<T>& ll){
	typename LinkedLinearList<T>::Iterator itr;

	for (itr = ll.begin(); itr != ll.end(); itr++)
		os << *itr << ", ";

	return os;
}
