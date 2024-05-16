package ac.um.ds;

public class HashTable<K extends Comparable<K> , V> implements Map<K, V>
{
	private class KeyValuePair<K, V>{
		public K key;
		public V value;
	}

	private int size;
	private HashFunction h1;
	private HashFunction h2;
	private float	maxLoadingFactor;
	private int		capacity;
	private boolean[] stateTable;	// 0 => empty ,  1 => full  
	private KeyValuePair<K, V> [] table;
	private boolean ch;
   public static boolean isaval(int x){
		int i;
		boolean ans=true;
		for(i=2;i<x/4+1;i++){if(x%i==0){ans=false;}}
		return ans;
	}
	public HashTable(HashFunction h1,
					 HashFunction h2,
					 float maxLoadingFactor,
					 int initCapacity) {
		// Write your code here
		ch=true;
		this.size=0;
		this.h1=h1;
		this.h2=h2;
		this.capacity=initCapacity;
		this.maxLoadingFactor=maxLoadingFactor;
		int i;
		this.stateTable = new boolean[initCapacity];
		for(i=0;i<initCapacity;i++){this.stateTable[i]=false;}
		this.table= new KeyValuePair[initCapacity];
	}
	
	@Override
	public void assign(K key, V value) {
		// Write your code here
		int x,y;
		int i;
		int check=0;
		i=0;
		while(this.stateTable[(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity]){
			y=(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity;
			if(this.table[y].key.compareTo(key)==0){
				check=1;
				this.table[y].value=value;
				
				break;
			}
			i++;
		}
		for(i=0;;i++){
			if(check==1) break;
			x= this.h1.hash(key) + this.h2.hash(key)*i;
			y=x%this.capacity;
			if(!this.stateTable[y]&&check==0){
			if(ch)	this.size++;
				this.table[y]= new KeyValuePair();
				this.table[y].key=key;
				this.table[y].value=value;
				this.stateTable[y]=true;
				break;
			}
		}
		float Occupiedpercentage =(float) this.size / (float)this.capacity;
	    if(Occupiedpercentage>this.maxLoadingFactor){
			KeyValuePair<K, V> [] oldk = this.table;
			 boolean[] olds=this.stateTable;
			 i=this.capacity*2;
			 while(true){
				 if(isaval(i)==true){break;}
				 i++;
			 }
			 int oldcp=this.capacity;
			 this.capacity=i;
			 this.stateTable = new boolean[this.capacity];
		     this.table= new KeyValuePair[this.capacity];
				for(i=0;i<oldcp;i++){
					ch=false;
				    if(olds[i]){this.assign(oldk[i].key, oldk[i].value);}
					ch=true;
				}
		}
	}

	@Override
	public void remove(K key) {
		// Write your code here	
		int i,j,y;
		V vv;
		K kk;
		y=0;
		i=0;
		while(this.stateTable[(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity]){
			y=(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity;
			if(this.table[y].key.compareTo(key)==0){
				this.table[y]=null;
				this.stateTable[y]=false;
				this.size--;
				break;
			}
			i++;
		}
		i=1;
		while(this.stateTable[(y+i*this.h2.hash(key))%this.capacity]){
		    j=(y+i*this.h2.hash(key))%this.capacity;
			vv=this.table[j].value;kk=this.table[j].key;
			this.table[j]=null;this.stateTable[j]=false;
			this.size--;
			this.assign(kk, vv);
		    i++;
		}
   }

	public int capacity()
	{
		return capacity;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean hasKey( K key)
	{
		// Write your code here	
		boolean ans=false;
		int i,y;
		i=0;
		while(this.stateTable[(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity]){
			y=(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity;
			if(this.table[y].key.compareTo(key)==0){
				ans= true;
				break;
			}
			i++;
		}
		return ans;
	}

	@Override
	public V get(K key)
	{
		// Write your code here
		int i,y;
		V ans=null;
	    i=0;
		while(this.stateTable[(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity]){
			y=(this.h1.hash(key)+this.h2.hash(key)*i)%this.capacity;
			if(this.table[y].key.compareTo(key)==0){
				ans= this.table[y].value;
				break;
			}
			i++;
		}
		return ans;
	}

	public void print()
	{
		for (int i = 0; i < capacity; i++)
			System.out.print (String.valueOf(i) + "\t");
		System.out.println();

		for (int i = 0; i < capacity; i++)
		{
			if (stateTable[i])
				System.out.print (String.valueOf(table[i].value) + "\t");
			else
				System.out.print ("*" + "\t");
		}
		System.out.println();
	}

	public String toString()
	{
		String s = "";
		s += "size:" + String.valueOf(size) + "\n";
		for (int i = 0; i < capacity - 1; i++)
		{
			if (stateTable[i])
				s += String.valueOf(table[i].key) + "," + String.valueOf(table[i].value) + ", ";
			else
				s += "-1, ";
		}
		if (stateTable[capacity - 1])
			s += String.valueOf(table[capacity - 1].key) + "," + String.valueOf((table[capacity - 1]).value);
		else
			s += "-1";
		return s;
	}
};
