package base;

import java.util.*;

public class Folder {
    private ArrayList<Note> notes;
    private String name;

    public Folder(String n){
        this.name = n;
        notes = new ArrayList<Note>();
    }

    public void addNote(Note nn){
        notes.add(nn);
    }

    public String getName(){
        return name;
    }

    public ArrayList<Note> getNotes(){
        return notes;
    }
    
    public String toString(){
        int nText = 0;
        int nImage = 0;

        for (Note n : notes){
            if (n instanceof ImageNote) nImage = nImage + 1;
            if (n instanceof TextNote) nText = nText + 1;

        }
        
        return name + ":" + nText + ":" + nImage;
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Note)) return false;
        Folder other = (Folder) obj;
        return Objects.equals(name, other.name);
    }
    
}
