package io.github.JFileRenamer.Util;

import io.github.JFileRenamer.Entity.TableRank;
import javafx.scene.control.Alert;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.ListIterator;

public class FileRenamer {
    private File renameFile(File file,String name) throws FileAlreadyExistsException {
        String parentPath = file.getParent();
        if(parentPath != null){
            parentPath = parentPath + "\\";
        }
        File newFile = new File(parentPath + name);
        boolean result = file.renameTo(newFile);
        if(result == false){throw new FileAlreadyExistsException("Error when renaming " + file.getName()); }
        return newFile;
    }

    public void renameFilesSansExtension(List<TableRank> list, String name){
        ListIterator<TableRank> iterator = list.listIterator();
        while(iterator.hasNext()){
            TableRank rank = iterator.next();
            File file = rank.getFile();
            String oldFileName = file.getName();
            String extension = null;
            if(oldFileName.contains(".")){
                extension = oldFileName.substring(oldFileName.lastIndexOf("."));
            }
            String newNameStr = name + "(" + iterator.nextIndex() + ")" + extension;
            try {
                rank.setFile(renameFile(file,newNameStr));
            } catch (FileAlreadyExistsException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error when renaming file");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void renameFiles(List<TableRank> list, String name){
        ListIterator<TableRank> iterator = list.listIterator();
        while(iterator.hasNext()){
            TableRank rank = iterator.next();
            String newNameStr = null;
            if(name.contains(".")){
                int index = name.lastIndexOf(".");
                newNameStr = name.substring(0,index) + "(" + iterator.nextIndex() + ")" + name.substring(index);
            }else{
                newNameStr = name + "(" + iterator.nextIndex() + ")";
            }
            try {
                rank.setFile(renameFile(rank.getFile(), newNameStr));
            }catch (FileAlreadyExistsException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error when renaming file");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
