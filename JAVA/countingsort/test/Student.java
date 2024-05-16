import ac.um.ds.IntegerKeyType;

class Student implements IntegerKeyType, Comparable<Student>{    
    private int studentNo;
    private String name;
    private int age;

    public Student (int studentNo, String name, int age) {
        this.studentNo = studentNo;
        this.name = name;
        this.age = age;
    }
    
    public int getKey(){
        return age;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return studentNo;
    }
    
    @Override
    public int compareTo(Student A) {
        if (age < A.getKey())
            return -1;
        else if (age > A.getKey())
            return 1;
        else
            return 0;
    }

}
