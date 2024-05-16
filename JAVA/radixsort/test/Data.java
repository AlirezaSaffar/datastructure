import ac.um.ds.IntegerKeyType;
import ac.um.ds.UnboundedInteger;

public class Data implements IntegerKeyType{
    private UnboundedInteger key;
    private int value;

    public Data(UnboundedInteger key, int value){
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public UnboundedInteger getKey() {
        return key;
    }

    @Override
    public String toString(){
        return "(" + key.getVal() + " , " + value + ")";
    }
    
}
