package io.github.JFileRenamer.Repository;

import io.github.JFileRenamer.Entity.TableRank;
import io.github.JFileRenamer.Util.FileRenamer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class RankRepository {
    private final ObservableList<TableRank> list = FXCollections.observableArrayList();
    private final FileRenamer renamer = new FileRenamer();

    public void renameSelectedFile(String name,boolean awareOfExtension){
        List<TableRank> selectedList = new LinkedList<>();
        ListIterator<TableRank> iterator = list.listIterator();
        TableRank rank = null;
        while(iterator.hasNext()){
            rank = iterator.next();
            if(rank.isIsChecked()){
                selectedList.add(rank);
            }
        }
        if(awareOfExtension){
            renamer.renameFiles(selectedList,name);
        }else{
            renamer.renameFilesSansExtension(selectedList,name);
        }
    }

    public final ObservableList<TableRank> getList() {
        return list;
    }

    public void addRank(TableRank rank){
        list.add(rank);
    }

    public void addFiles(List<File> fileList){
        ListIterator<File> iterator = fileList.listIterator();
        TableRank rank = null;
        while(iterator.hasNext()){
            rank = new TableRank(iterator.next());
            list.add(rank);
        }
    }
}
