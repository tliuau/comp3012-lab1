package base;

import java.util.*;

public class NoteBook {
    private ArrayList<Folder> folders;

    public NoteBook(){
        folders = new ArrayList<Folder>();
    }

    public boolean createTextNote(String folderName, String title){
        TextNote note = new TextNote(title);
        return insertNote(folderName,note);
    }

    public boolean createTextNote(String folderName, String title, String content){
        TextNote note = new TextNote(title);
        note.setContent(content);
        return insertNote(folderName,note);
    }

    public boolean createImageNote(String folderName, String title){
        ImageNote note = new ImageNote(title);
        return insertNote(folderName,note);
    }


    public ArrayList<Folder> getFolders(){
        return folders;
    }

    public boolean insertNote(String folderName, Note note){
        Folder f = null;

        //Step 1: check in there is folderName in folders
            //Case 1: there is
        for (Folder f1:folders){
            if (f1.getName() == folderName){
                //Get the folder
                //System.out.println("got folder " + folderName);
                f = f1;
                break;
            }
        }

            //Case 2: there isn't
        if (f == null){
            f = new Folder(folderName);
            folders.add(f);
            //System.out.println("No folder named " + folderName);
        }

        //Step 2: check if there is a note in the folder note
        for (Note n:f.getNotes()){
            if (n.equals(note)){
                System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
                return false;
            }
        }

        f.addNote(note);
                //System.out.println("No note named " + note.getTitle());
                return true;
    }
    

    public void sortFolders(){
        for (Folder f1:folders){
            f1.sortNotes();
        }

        Collections.sort(folders);
    }

    public List<Note> searchNotes(String keywords){
        String [] kw = new String[0];
        int x = 0;

        //CONVERT keywords into kw arr
        for (int i = 0; i < keywords.length(); ++i){
            if (keywords.charAt(i) == '\n') {x = x + 1;}
            else {kw[x] = kw[x] + keywords.charAt(i);}
        }

        List<Note> results = new ArrayList<Note>();


        //Then see if there is an or
        for (int j = 0; j < kw.length; ++j){ //J = current note being searched
            for (Folder n: folders){
                List<Note> res = n.searchNotes(keywords);
                for (Note m: res){
                    results.add(m);
                }
            }
        }
        return results;
    }
}
