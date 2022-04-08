package base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.io.PrintWriter;


public class TextNote extends Note implements Serializable {
    private String content;

    public TextNote(String title){
        super(title);
    }

    public TextNote(String title, String content){
        super(title);
        this.content = content;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String s){
        this.content = s;
    }

    //Lab4

    public TextNote(File f){
        super (f.getName());
        this.content = getTextFromFile(f.getAbsolutePath());
    }

    public String getTextFromFile (String absolutePath){
        String result = "";
        System.out.println("getTextfromfile called" );

        try {
            File file = new File(absolutePath);

            if(!file.canRead()){ System.out.println("File unreadable" );
            file.setReadable(true);}

            BufferedReader br = new BufferedReader(new FileReader(file));
            System.out.println("File opended: " + file);
            while ((result = br.readLine())!= null){
                result = result + br.readLine();
            }
            //result.readObject(file);

            br.close();
        }

        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception getTextFromFile() for " + absolutePath);
        }
        
        //TODO

        //Load textnote from file f
        //Tile of textnote = name of file
        //Content = content
        return  result;
    }

    public void exportTextToFile(String pathFolder){
        System.out.println("Export text to file called");
        File file = new File("C:/Users/hp-pc/Desktop/lab4" + pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
        System.out.println("File created & ready fpr export: " + file);

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            //out.writeObject(this);
            PrintWriter o = new PrintWriter(file);
            o.println(this.getContent());
            System.out.println("content written");

            out.close();
            o.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
