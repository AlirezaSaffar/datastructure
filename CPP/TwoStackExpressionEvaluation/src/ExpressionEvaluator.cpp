
#include "ExpressionEvaluator.h"
#include "Delimeter.h"
#include "MathFunction.h"
#include <stack>
#include <vector>
using namespace std;

void ExpressionEvaluator::infix2Postfix(const vector<Token*>& infixExpression, vector<Token*>& postfixExpression)
{
	// Write your code here
	Token *temp;
    	stack<Token*> *st=new stack<Token*>();
    	int i,j;
    	j=0;
    	Token *to;
    	int n=infixExpression.size();
    	for(i=0;i<n;i++){
        to =infixExpression.at(i);

    	switch(to->mType){

    	case Token::NUMERIC_CONST:
postfixExpression.push_back(to);
    	    j++;
    	    break;
    	case Token::VARIABLE:
postfixExpression.push_back(to);
j++;
    	
    		break;
    	case Token::OPERATOR:

    	while(true){
    			
    				if(st->empty()==true){break;}
    				if( (st->top()->mType==Token::OPERATOR || st->top()->mType==Token::FUNCTION )==false){break;}
    			    if(((  (Operator*)st->top() )->mPrecedence < ((Operator*)to)->mPrecedence)){break;}
  
    				if(((Operator*)to)->mPrecedence == ( (Operator*)st->top() )->mPrecedence){
    					if(( (Operator*)st->top() )->mAssociativity==Operator::ASSOC_RIGHT){break;}
}
                temp = st->top();
    			st->pop();
    			postfixExpression.push_back(temp);
	
				j++;
    		}
    		st->push(to);
    
    		break;
    	case Token::FUNCTION:

    		st->push(to);
    		break;
    	case Token::DELIMETER:

         	int check=0;
    		if(((Delimeter*)to)->mMark=='('){
    			st->push(to);
    			check=1;
    		}else{

    			
    			while(true){
    				if(st->empty()){break;}

    				if(st->top()->mType== Token::DELIMETER){
    					if(((Delimeter*)st->top())->mMark=='('){break;}
    				}

    			
    				temp = st->top();
    				st->pop();
    				
    				postfixExpression.push_back(temp);
    				j++;


    			}


    		}
    		if( !st->empty() &&check==0){

    		st->pop();

    		}
    	
    		break;
}
}
   while( !st->empty()){
			temp = st->top();
			st->pop();
			postfixExpression.push_back(temp);
			j++;
		}


}



double  ExpressionEvaluator::evaluatePostfixExpression(vector<Token*> s, map<string, double> variableValues)
{
	// Write your code here


	double ans=0;
    	Token *to;
    	double dd;



    	int n=s.size();
    	int i,j,k;
    	double x,y;
    	stack<double> *st = new stack<double>();
    	for(i=0;i<n;i++){
    	to=	s.at(i);
       switch(to->mType){

       case Token::NUMERIC_CONST:
    	   st->push(((NumericConstant*)to)->mValue);
    	   break;
       case Token::VARIABLE:
    	
st->push(variableValues[((VariableName*)to)->mName]);
    	   break;
       case Token::OPERATOR:

    	   switch( ((Operator*)to)->mName[0]  ){

    	   case '-':
    	       x=st->top();
    		   st->pop();

    		   y=st->top();
    		   st->pop();

    		    dd=(( MinusOperator*)((Operator*)to))->evaluate(y,x);
    		   st->push(dd);
    		   break;
    	   case '_':
    		   x=st->top();
    		   st->pop();

    		   dd=(( UnaryMinusOperator*)((Operator*)to))->evaluate(x,x);
    		   st->push(dd);
    		   break;
    	   case '+':
    		   x=st->top();
    		   st->pop();

    		   y=st->top();
    		   st->pop();
    		   dd=(( PlusOperator*)((Operator*)to))->evaluate(y,x);
    		   st->push(dd);
    		   break;
    	   case '/':
    		   x=st->top();
    		   st->pop();

    		   y=st->top();
    		   st->pop();
    		   dd=(( DivisionOperator*)((Operator*)to))->evaluate(y,x);
    		   st->push(dd);
    		   break;
    	   case '*':
    		   x=st->top();
    		   st->pop();

    		   y=st->top();
    		   st->pop();
    		   dd=(( MultiplicationOperator*)((Operator*)to))->evaluate(y,x);
    		   st->push(dd);
    		   break;
    	   case 'l':
    		   break;
    	   case 's':
    		   break;
    	   case 'c':
    		   break;
    	   case '^':
    		  x=st->top();
    		   st->pop();

    		   y=st->top();
    		   st->pop();
    		   dd=(( PowerOperator*)((Operator*)to))->evaluate(y,x);
    		   st->push(dd);
    		   break;



    	   }


    	   break;
       case Token::FUNCTION:

    	   switch(((Operator*)to)->mName[0]){
    	   case 'l':
    		   x=st->top();
    		   st->pop();
    		   dd=(( LnFunc*)((Operator*)to))->evaluate(x,x);
    		   st->push(dd);
    		   break;
    	   case 's':
    		   x=st->top();
    		   st->pop();
    		   dd=(( SinFunc*)((Operator*)to))->evaluate(x,x);
    		   st->push(dd);
    		   break;
    	   case 'c':
    		  x=st->top();
    		   st->pop();
    		   dd=(( CosFunc*)((Operator*)to))->evaluate(x,x);
    		   st->push(dd);
    		   break;
    	   case 'e':
    		  x=st->top();
    		   st->pop();
    		   dd=(( ExpFunc*)((Operator*)to))->evaluate(x,x);
    		   st->push(dd);
    		   break;

    	   }

    	   break;
       case Token::DELIMETER:

    	   break;

    	}
    	}
    	ans=st->top();
    	st->pop();


    	return ans;

}


double ExpressionEvaluator::evaluateExpression(string s, map<string, double> variableValues)
{
	double val;
	vector<Token*> inTokens, postTokens;
	tokenizeString (s, inTokens);
	infix2Postfix (inTokens, postTokens);
	val = evaluatePostfixExpression (postTokens, variableValues);
	// postTokens are a rearrangement of inTokens. So, we don't delete them
	for (unsigned int i=0; i < inTokens.size(); i++)
		delete inTokens[i];

	return val;
}

string ExpressionEvaluator::infix2Postfix(string s)
{
	unsigned int i;
	string out;
	vector<Token*> inTokens, postTokens;
	tokenizeString (s, inTokens);
	infix2Postfix (inTokens, postTokens);
	out = tokenSeq2String (postTokens);

	// postTokens are a rearrangement of inTokens. So, we don't delete them
	for (i=0; i < inTokens.size(); i++)
		delete inTokens[i];

	return out;
}

string ExpressionEvaluator::tokenSeq2String(const vector<Token*>& vt)
{
	unsigned int i;
	string s;
	for (i=0; i < vt.size();i++)
	{
		s += ' ';
		s += vt[i]->toText();
	}
	return s;
}


void ExpressionEvaluator::tokenizeString(string s, vector<Token*>& out)
{
	unsigned int i;
	stringstream ss;

	for (i=0; i < s.size(); i++)
		s[i] = tolower(s[i]);
	s.push_back(EOF);
	ss << s;
	Token *token = NULL;

	token = getNextToken(ss,token);
	while (token)
	{
		out.push_back(token);
		token = getNextToken(ss,token);
	}
}

Token* ExpressionEvaluator::getNextToken(stringstream& ss, Token* lastToken)
{
	int state=0;
	Operator*	op;
	string tokenStr;
	char ch = ' ';
	bool dotIsSeen;
	while (1)
	{
		ss.read(&ch,1);
		switch (state)
		{
		// Initial State
		case 0:
			if (ch<='9' && ch>='0'){	tokenStr+=ch; state=1; dotIsSeen=false; break;}

			if ((ch<=(unsigned char)'z' && ch>=(unsigned char)'a')||
				(ch<=(unsigned char)'Z' && ch>=(unsigned char)'A')/*||
																  (ch==(unsigned char)'_')*/)
			{	tokenStr+=ch;	 state=2;break;}
			else
			{
				switch (ch)
				{
				case '(':return new LeftParanthesis();
				case ')':return new RightParanthesis();
				case '^':return new PowerOperator();
				case '+':return new PlusOperator();
				case '*':return new MultiplicationOperator();
				case '/':return new DivisionOperator();
				case '-':
					if (lastToken == NULL || dynamic_cast<LeftParanthesis*>(lastToken)
						|| (op=dynamic_cast<Operator*>(lastToken) /*&& op->mIsUnary*/))
						return new UnaryMinusOperator();
					else
						return new MinusOperator();
				case '.':state=1;  dotIsSeen=true; break;
				case EOF:	return NULL;
				case 10:
				case 13:
				case 32:
				case (int)('\t'):
					break;
				default:
					{
						char buff[30]="UnAllowed character No:     ";
						buff[25]='0'+ch/100;	buff[26]='0'+(ch%100)/10;	buff[27]='0'+ch%10;	buff[28]=0;
						throw std::runtime_error(buff);
					}
				}
			}
			break;

		// Number
		case 1:
			if (ch == '.')
			{
				if (dotIsSeen)
					throw (std::runtime_error("Numeric string with two dots!"));
				else
				{
					dotIsSeen = true;
				}

			}
			else if (ch>'9' || ch<'0')
			{
				double val;
				ss.putback (ch);
				val = atof (tokenStr.c_str());
				return new NumericConstant (val);
			}

			tokenStr+=ch;
			break;

		// String
		case 2:
			if ((ch<=(unsigned char)'z' && ch>=(unsigned char)'a')
				||(ch<=(unsigned char)'Z' && ch>=(unsigned char)'A')
				/*|| (ch=='_')*/||(ch<='9' && ch>='0'))
			{	tokenStr+=ch; }
			else
			{
				ss.putback (ch);

				if (tokenStr == "sin")
					return new SinFunc();
				else if (tokenStr == "cos")
					return new CosFunc();
				else if (tokenStr == "exp")
					return new ExpFunc();
				else if (tokenStr == "ln")
					return new LnFunc();
				else
					return new VariableName (tokenStr);
			}
			break;
		}
	}
}



