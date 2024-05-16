package ac.um.ds;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;




public class ExpressionEvaluator<T> {
    public double evaluateExpression(String s, HashMap<String, Double> variableValues) throws Exception {
        double val;
        Vector<Token> inTokens = new Vector<Token>();
        Vector<Token> postTokens = new Vector<Token>();
        tokenizeString(s, inTokens);
        infix2Postfix(inTokens, postTokens);
        val = evaluatePostfixExpression(postTokens, variableValues);
        // postTokens are a rearrangement of inTokens. So, we don't delete them
        for (int i = 0; i < inTokens.size(); i++) {
            inTokens.remove(i);
        }

        return val;
    }

    public String infix2Postfix(String s) throws Exception {
        int i;
        String out;
        Vector<Token> inTokens = new Vector<Token>();
        Vector<Token> postTokens = new Vector<Token>();
        tokenizeString(s, inTokens);
        infix2Postfix(inTokens, postTokens);
        out = tokenSeq2String(postTokens);

        // postTokens are a rearrangement of inTokens. So, we don't delete them
        for (i = 0; i < inTokens.size(); i++) {
            inTokens.remove(i);
        }

        return out;
    }


    public void tokenizeString(String s, Vector<Token> out) throws Exception
    {
        int i;
        char[] ch = new char[s.length() + 1];
        StringBuilder ss = new StringBuilder();

        s = s.toLowerCase();

        ss.append(s);
        ss.append('\0');

        Token token = null;
        token = getNextToken(ss, token);
        while (token != null) {

            out.add(token);
            token = getNextToken(ss, token);

        }
    }


    public String tokenSeq2String(Vector<Token> vt) {
        int i;
        String s = "";
        for (i = 0; i < vt.size(); i++) {
            s += ' ';
            s += vt.get(i).toText();
        }
        return s;
    }


    public double evaluatePostfixExpression(Vector<Token> s, HashMap<String, Double> variableValues) {
        // Write your code here
    	double ans=0;
    	Token to;
    	double dd;
    	int n=s.size();
    	int i;
    	double x,y;
    	Stack<Double> st = new Stack<Double>();
    	for(i=0;i<n;i++){
    	to=	s.elementAt(i);
       switch(to.mType){
       
       case NUMERIC_CONST:
    	   st.push(((NumericConstant)to).mValue);
    	   break;
       case VARIABLE:
    	   st.push(variableValues.get( ((VariableName)to).mName ));
    	   break;
       case OPERATOR:
    	   switch( ((Operator)to).mName.charAt(0)  ){
    	   case '-':
    		   x=st.pop();
    		   y=st.pop();
    		    dd=(( MinusOperator)((Operator)to)).evaluate(y,x);
    		   st.push(dd);
    		   break;
    	   case '_':
    		   x=st.pop();
    		   
    		   dd=(( UnaryMinusOperator)((Operator)to)).evaluate(x,x);
    		   st.push(dd);
    		   break;
    	   case '+':
    		   x=st.pop();
    		   y=st.pop();
    		   dd=(( PlusOperator)((Operator)to)).evaluate(y,x);
    		   st.push(dd);
    		   break;
    	   case '/':
    		   x=st.pop();
    		   y=st.pop();
    		   dd=(( DivisionOperator)((Operator)to)).evaluate(y,x);
    		   st.push(dd);
    		   break;
    	   case '*':
    		   x=st.pop();
    		   y=st.pop();
    		   dd=(( MultiplicationOperator)((Operator)to)).evaluate(y,x);
    		   st.push(dd);
    		   break;
    	   case 'l':
    		   break;
    	   case 's':
    		   break;
    	   case 'c':
    		   break;
    	   case '^':
    		   x=st.pop();
    		   y=st.pop();
    		   dd=(( PowerOperator)((Operator)to)).evaluate(y,x);
    		   st.push(dd);
    		   break;
    	   }
    	   break;
       case FUNCTION:
    	   
    	   switch(((Operator)to).mName.charAt(0)){
    	   case 'l':
    		   x=st.pop();
    		   dd=(( LnFunc)((Operator)to)).evaluate(x,x);
    		   st.push(dd);
    		   break;
    	   case 's':
    		   x=st.pop();
    		   dd=(( SinFunc)((Operator)to)).evaluate(x,x);
    		   st.push(dd);
    		   break;
    	   case 'c':
    		   x=st.pop();
    		   dd=(( CosFunc)((Operator)to)).evaluate(x,x);
    		   st.push(dd);
    		   break;
    	   case 'e':
    		   x=st.pop();
    		   dd=(( ExpFunc)((Operator)to)).evaluate(x,x);
    		   st.push(dd);
    		   break;
    		   
    	   }
    	   
    	   break;
       case DELIMETER:
    	  
    	   break;
    		
    	}
    	}
    	ans=st.pop();
    	
    	
    	return ans;
    }


    public void infix2Postfix(Vector<Token> infixExpression, Vector<Token> postfixExpression) throws Exception {
        // Write your code here
    	Token temp;
    	Stack<Token> st=new Stack<Token>();
    	int i,j;
    	j=0;
    	PowerOperator power = new PowerOperator();
    	Token to;
    	int n=infixExpression.size();
    	for(i=0;i<n;i++){
    	to=	infixExpression.elementAt(i);
    		
    	switch(to.mType){
    	
    	case NUMERIC_CONST:
    		
    		postfixExpression.add(j, to);
    		j++;
    	
    		break;
    	case VARIABLE:
    		
    		postfixExpression.add(j, to);
    		j++;
    	
    		break;
    	case OPERATOR:
    		
    
    			while(true){
    			
    				if(st.isEmpty()==true){break;}
    				if(st.peek() instanceof Operator==false){break;}
    			    if(((  (Operator)st.peek() ).mPrecedence < ((Operator)to).mPrecedence)){break;}
    		          if(((Operator)to).mPrecedence == ( (Operator)st.peek() ).mPrecedence){
    					if(( (Operator)st.peek() ).mAssociativity==power.mAssociativity){break;}
    					}
    			temp = st.pop();
				postfixExpression.add(j, temp);
				j++;
    		}
    		st.push(to);
    	
    		break;
    	case FUNCTION:
    		st.push(to);
    		break;
    	case DELIMETER:
    		
    	
    		int check=0;
    		if(((Delimeter)to).mMark=='('){
    			st.push(to);
    			check=1;
    		}else{
    			while(true){
    				if(st.isEmpty()){break;}
    				if((st.peek() instanceof Delimeter)){
    					if(((Delimeter)st.peek()).mMark=='('){break;}
    				}
    				temp = st.pop();
    				postfixExpression.add(j, temp);
    				j++;
    			}
    		}
    		if( !st.isEmpty() &&check==0){
    		st.pop();
    		}
    	      break;
    	}
    	}
        while( !st.isEmpty()){
			temp = st.pop();
			postfixExpression.add(j, temp);
			j++;
		}
    }


    public Token getNextToken(StringBuilder ss, Token lastToken) throws Exception {
        int state = 0;
        int length = ss.length();

        Operator op;
        String tokenStr = "";
        char ch = ' ';
        String FinalString = "";
        Stack<Character> charStack = new Stack<Character>();
        boolean dotIsSeen = false;

        while (true) {
            ch = ss.charAt(0);
            ss.deleteCharAt(0);
            switch (state) {
                // Initial State
                case 0:
                    if (ch <= '9' && ch >= '0') {
                        tokenStr += ch;
                        state = 1;
                        dotIsSeen = false;
                        break;
                    }
                    if ((ch <= (byte) 'z' && ch >= (byte) 'a') || (ch <= (byte) 'Z' && ch >= (byte) 'A')) {
                        tokenStr += ch;
                        state = 2;
                        break;
                    } else {
                        switch (ch) {
                            case '(':
                                return new LeftParanthesis();
                            case ')':
                                return new RightParanthesis();
                            case '^':
                                return new PowerOperator();
                            case '+':
                                return new PlusOperator();
                            case '*':
                                return new MultiplicationOperator();
                            case '/':
                                return new DivisionOperator();
                            case '-':
                                if (lastToken == null || lastToken instanceof LeftParanthesis || (lastToken instanceof Operator) && ((op = (Operator) lastToken) != null)) {
                                    return new UnaryMinusOperator();
                                } else {
                                    return new MinusOperator();
                                }
                            case '.':
                                state = 1;
                                dotIsSeen = true;
                                break;
                            case '\0':
                                return null;
                            case 10:
                            case 13:
                            case 32:
                            case (int) ('\t'):
                                break;
                            default: {
                                String buff = "UnAllowed character No:     ";
                                buff += '0' + ch / 100;
                                buff += '0' + (ch % 100) / 10;
                                buff += '0' + ch % 10;
                                buff = buff.substring(0, 28);
                                throw new Exception(buff);
                            }
                        }
                    }
                    break;

                // Number
                case 1:
                    if (ch == '.') {
                        if (dotIsSeen) {
                            throw new Exception("Numeric string with two dots!");
                        } else {
                            dotIsSeen = true;
                        }

                    } else if (ch > '9' || ch < '0') {
                        double val;
                        ss.insert(0, ch);
                        val = Double.parseDouble(tokenStr);
                        return new NumericConstant(val);
                    }

                    tokenStr += (char) ch;
                    break;

                // String
                case 2:
                    if ((ch <= (byte) 'z' && ch >= (byte) 'a') || (ch <= (byte) 'Z' && ch >= (byte) 'A') || (ch <= '9' && ch >= '0'))
                    {
                        tokenStr += (char) ch;
                    } else {
                        ss.insert(0, ch);
                        if (tokenStr.equals("sin")) {
                            return new SinFunc();
                        } else if (tokenStr.equals("cos")) {
                            return new CosFunc();
                        } else if (tokenStr.equals("exp")) {
                            return new ExpFunc();
                        } else if (tokenStr.equals("ln")) {
                            return new LnFunc();
                        } else {
                            return new VariableName(tokenStr);
                        }
                    }
                    break;
            }
        }
    }
}