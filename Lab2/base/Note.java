package base;

import java.util.*;

public class Note implements Comparable<Note>{
    private Date date;
    private String title;

    public Note(String title){
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
    }

    public String getTitle(){
        return title;
    }

    public int hashCode(){
        return Objects.hash(title);
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Note)) return false;
        Note other = (Note) obj;
        return Objects.equals(title, other.title);
    }

    @Override
    public int compareTo(Note o){
        //Compares this note to note o based on date
        //Return 1 of greater than o
        //Return 0 = equal
        //Return -1 if less than
        String d1 = date.toString();
        String d2 = o.date.toString();

        if (d1.compareTo(d2) >= 1) return 1;
        else if (d1.compareTo(d2) <= -1) return -1;
        else return 0;

    }
    
}
