package model;
import java.text.*;

public class Person
{   private String name;
    private int start;
    private int end;

    public Person(String name, int start, int end)
    {   
        this.name = name;
        this.start = start;
        this.end = end; 
    }

    public boolean getsOn(int stop)
    {   
        return start == stop;  
    }

    public boolean getsOff(int stop)
    {   
        return end == stop;  
    }

    public boolean isOn(int stop)
    {   
        return (start <= stop) && (end >= stop); 
    }

    public String name()
    {   
        return name;    
    }
}
