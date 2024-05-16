package ac.um.ds;

public class UnboundedInteger{
	private String val;

	public UnboundedInteger(int value) {
		this.val = Integer.toString(value);
	}

	private static int pow(int n){
		int i,ans;
		ans=1;
		for(i=0;i<n;i++){
			ans= ans*10;
		}
		return ans;
	}
	public UnboundedInteger(String value) {
		this.val = value;
	}

	public int getDigit(int r, int j){ 
        //Write your code here.
		int ans,i,b;
		ans=0;
	int	n=  val.length();
		for(i=n-1-r*(j-1);i>n-1-r*j;i--){
			if(i< 0){b=0;}else{
				b=Integer.parseInt(String.valueOf(this.val.charAt(i))) ;
			}
			ans+= b * pow(n-1-r*(j-1)-i);
		}
		return ans;
	}

	public String getVal() {
		return this.val;
	}

}
