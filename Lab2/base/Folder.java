package base;

import java.util.*;

public class Folder implements Comparable<Note>{
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

    @Override
    public int compareTo(Folder o){
        //Compares this note to note o based on date
        //Return 1 of greater than o
        //Return 0 = equal
        //Return -1 if less than
        String d1 = name;
        String d2 = o.name;

        if (d1.compareTo(d2) >= 1) return 1;
        else if (d1.compareTo(d2) <= -1) return -1;
        else return 0;

    }

    public void sortNotes(){
        Collections.sort(notes);
    }

    public List<Note> searchNotes(String keywords){
        String [] kw;
        int x = 0;

        //While loop to log all of the char in keywords
        for (int i = 0; i < keywords.length(); ++i){
            if (keywords.charAt(i) == "/n") {x = x + 1;}
            else {kw[x] = kw[x] + keywords.charAt(i);}
        }
        //If space, put word into array
        //Stop when there is no words
        ArrayList<Note> results [];


        //Then see if there is an or
        for (int j = 0; j < kw.length; ++j){
            if (kw[j+1] == "OR") {
                
                continue;
            }
            if (   );
        }
    }
    
}
