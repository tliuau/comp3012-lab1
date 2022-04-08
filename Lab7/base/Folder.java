package base;

import java.io.Serializable;
import java.util.*;
//import org.apache.commons.lang3.StringUtils;
//import java.util.ArrayList.*;

public class Folder implements Comparable<Folder>, Serializable{
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
        //System.out.println("Search called");
        List<String> kw_list = new ArrayList<String>();

        //System.out.println("Searching for " + keywords);

        //Separate keyword string into separate words, in arraylist "kw_list"
        String wordLog = "";
        for (int i = 0; i < keywords.length(); ++i){
            if (keywords.charAt(i) == ' '){
                kw_list.add(wordLog);
                //System.out.println(wordLog);
                wordLog = "";
                continue;
            }
            wordLog = wordLog + keywords.charAt(i);
            //System.out.println(wordLog);
        }
        kw_list.add(wordLog);
        
        //System.out.println(kw_list);
        //System.out.println(kw_list.size());

        //Start construction search content
        String [][] searchList = new String [kw_list.size()][2];
        int count = 0; //counter for the size of array

        for (int i = 0; i < kw_list.size(); ++i){

            //System.out.println(i);
            if ((i > 0) && (kw_list.get(i).equalsIgnoreCase("or") || kw_list.get(i-1).equalsIgnoreCase("or"))){
                continue;
            }

            if (i+2 < kw_list.size()){
            if (kw_list.get(i+1).equalsIgnoreCase("or") && i+2 < kw_list.size()){
                searchList[count][1] = kw_list.get(i+2);
            }}

            searchList[count][0] = kw_list.get(i); //add word to searchList
            //System.out.println(searchList[count][0]);
            count += 1;
        }
        //System.out.println(searchList);

        //Define result array
        List<Note> results = new ArrayList<Note>();

        //Search notes
        List<Note> allNotes = new ArrayList<Note>();
        allNotes.addAll(notes);


        for (Note n: notes){
            //System.out.println("entered for loop");
            if (n instanceof ImageNote){
                //System.out.println("image" + n.getTitle());
                for (int i = 0; i < count; ++i){
                    //System.out.println("now verifying "+ n.getTitle().toLowerCase() + " with search term " + searchList[i][0].toLowerCase());

                    //if
                    if (searchList[i][1] == null){
                        if (n.getTitle().toLowerCase().contains(searchList[i][0].toLowerCase())){
                            //System.out.println("note verified");
                         }
                        else {
                            allNotes.remove(n);
                            //System.out.println("note removed from list");
                            break;
                        }
                    }
                    else {
                        if (n.getTitle().toLowerCase().contains(searchList[i][0].toLowerCase()) || n.getTitle().toLowerCase().contains(searchList[i][1].toLowerCase())){
                            //System.out.println("note verified");
                         }
                         else {
                            allNotes.remove(n);
                            //System.out.println("note removed from list");
                            break;
                        }
                    }
                }
            }

            if (n instanceof TextNote){
                TextNote x = (TextNote) n;
                //System.out.println("text" + n.getTitle());
                for (int i = 0; i < count; ++i){
                    //System.out.println("now verifying "+ n.getTitle().toLowerCase() + " with search term " + searchList[i][0].toLowerCase());

                    if (searchList[i][1] == null){
                        if (x.getTitle().toLowerCase().contains(searchList[i][0].toLowerCase()) || x.getContent().toLowerCase().contains(searchList[i][0].toLowerCase())){
                        //System.out.println("note verified");
                    }
                    else {
                        allNotes.remove(n);
                        //System.out.println("note removed from list");
                        break;
                    }
                    }
                    else {
                        if (x.getTitle().toLowerCase().contains(searchList[i][0].toLowerCase()) || x.getTitle().toLowerCase().contains(searchList[i][1].toLowerCase())
                    || x.getContent().toLowerCase().contains(searchList[i][0].toLowerCase()) || x.getContent().toLowerCase().contains(searchList[i][1].toLowerCase())){
                        //System.out.println("note verified");
                    }
                    else {
                        allNotes.remove(n);
                        //System.out.println("note removed from list");
                        break;
                    }
                    }
                    
                }
            }
            
        }
        results.addAll(allNotes);

        return results;
    }
}
