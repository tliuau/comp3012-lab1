package base;

import java.util.*;

public class Folder implements Comparable<Folder>{
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
        keywords = keywords + "\n";
        //1. Create String list of keywords, in order to separate into different words
        List<String> kw;
        kw = new ArrayList<String>();

        //2. Char list s to log single keywords
        String s = "";

        //While loop to log all of the char in keywords
        for (int i = 0; i < keywords.length(); ++i){
            //When space or at end of keywords:
                //a. Convert s-list into string
                //b. append to kw
                //c. clear s
            if (keywords.charAt(i) == ' ' || i == keywords.length()-1) {
                kw.add(s);
                s = "";
            }

            else {
                //If there is no space, add chars into a string
                s = s + keywords.charAt(i);
            }
        }
        

        //Searched notes list
        ArrayList<Note> results;
        results = new ArrayList<Note>();


        //3. Searching

            //If word == or: 

        for (int i = 0; i < kw.size(); ++i){ //For each word in kw
            //System.out.println(k);
            //System.out.println(kw.get(i));

            //If there is an or, get sandwiching terms into or box
            if ( kw.get(i)== "OR" || kw.get(i) == "or") { 
                for (Note n : notes){//Search the 2 types of notes
                    if (n instanceof ImageNote){
                        String title = n.getTitle();
                        if (title.contains(kw.get(i-1)) == true || title.contains(kw.get(i+1))==true){
                            results.add(n);
                        }
                    }
                    else if (n instanceof TextNote){
                        String title = n.getTitle();
                        String content = ((TextNote)n).getContent();
                        if((title.contains(kw.get(i-1)) == true || content.contains(kw.get(i-1)) == true) || (title.contains(kw.get(i+1)) == true || content.contains(kw.get(i+1)) == true)){
                            results.add(n);
                        }
                    }
                } //results append notes that has either of the or-words

                continue;
            }

            else{ //If there's no or
                for (Note n : notes){
                    if (n instanceof ImageNote){
                        String title = n.getTitle();
                        if (title.contains(kw.get(i)) == true 
                            && title.contains(kw.get(i+1)) 
                            && kw.get(i+1) != "OR" 
                            && kw.get(i+1) != "or"){

                            results.add(n);
                        }
                    }
                    else if (n instanceof TextNote){
                        String title = n.getTitle();
                        String content = ((TextNote)n).getContent();
                        if(title.contains(kw.get(i)) == true || content.contains(kw.get(i)) == true
                            //&& title.contains(kw.get(i+1)) == true  || content.contains(kw.get(i+1)) == true
                            //&& kw.get(i+1) != "OR" 
                            //&& kw.get(i+1) != "or"
                            ){

                            results.add(n);
                        }
                    }
                }
            }
        }
        return results;
    
    }
    
    
}
