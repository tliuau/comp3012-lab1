   /*
        //Step 1
        if (folders.contains(folderName) == true){
            int x = folders.indexOf(folderName);
            folders.get(x); //if there is folderName in folders, get.

            //Step 2
            if (folders.get(x).getNotes().equals(note)){
                System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
                return false;
                //If there i
            }
            else {
                folders.get(x).addNote(note);
                return true;
            }
        }

        //Step 1
        else {
            Folder nf = new Folder(folderName);
            folders.add(nf);
            System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
            return false;
            //folders.get(0).addNote(note);
            //return true;
        }  
        */