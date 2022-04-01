package base;

//import java.nio.charset.StandardCharsets;
import java.util.*;

//import org.w3c.dom.Text;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NoteBook implements java.io.Serializable{
    private ArrayList<Folder> folders;
    //private final long serialVersionUID = 1L;

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

        List<Note> results;
        results = new ArrayList<Note>();

        for (Folder f:folders){
            List<Note> res;
            res = f.searchNotes(keywords);
            results.addAll(res);
        }
        return results;
    }

    //Lab4
    /*public long getID(){
        return this.serialVersionUID;
    }*/

    public boolean save(String file){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        NoteBook n = new NoteBook();
        n.folders = this.getFolders();

        try {
            fos = new FileOutputStream(new File("C:/Users/hp-pc/Desktop/JAVA-LAB/" + file));
            out = new ObjectOutputStream(fos);

            out.writeObject(n);

            out.close();
            fos.close();

            //System.out.println("serialized");
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public NoteBook(String file) {
        //System.out.println("Serial cons called in NoteBook");
        //TODO

        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);

            //in.setID(((Integer) row.get("ID")).longValue());
            NoteBook n = (NoteBook) in.readObject();
            this.folders = n.getFolders();
            System.out.println("Folder gotten: " + this.folders);
            //this.serialVersionUID = n.getID();

            in.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return n;
    }

    
}
