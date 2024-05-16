#pragma once
#include <string>

using namespace std;

class UnboundedInteger{
private:
    string val;
       int pow(int n){
		int i,ans;
		ans=1;
		for(i=0;i<n;i++){
			ans= ans*10;
		}
		return ans;
	}

public:
    UnboundedInteger(int value) {
        this->val = to_string(value);
    }

    UnboundedInteger(string value) {
        this->val = value;
    }

    virtual int getDigit(int r, int j) {
		//Write your code here.
		int ans,i,b;
		ans=0;
    int	n=  val.length();
		for(i=n-1-r*(j-1);i>n-1-r*j;i--){
			if(i< 0){ b=0;   }else{
            b= val[i]-48;
}
			ans+= b * pow(n-1-r*(j-1)-i);
		}
		return ans;
    }

    virtual string getVal() {
        return this->val;
    }
};
