package base;

import java.util.*;

public class Note {
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
    
}
