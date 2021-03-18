package io.github.JFileRenamer.Entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;

public final class TableRank {
    private SimpleBooleanProperty isChecked;
    private SimpleStringProperty fileName;
    private SimpleBooleanProperty action;
    private File file;

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public void setFile(File file) {
        this.file = file;
        fileName.set(file.getName());
    }

    public boolean isAction() {
        return action.get();
    }

    public SimpleBooleanProperty actionProperty() {
        return action;
    }

    public void setAction(boolean action) {
        this.action.set(action);
    }

    @Override
    public String toString() {
        return "TableRank{" +
                "isChecked=" + isChecked +
                ", fileName=" + fileName +
                ", file=" + file +
                '}';
    }

    public TableRank(File file) {
        this.file = file;
        isChecked = new SimpleBooleanProperty(true);
        fileName = new SimpleStringProperty(file.getName());
        action = new SimpleBooleanProperty(true);
    }

    public boolean isIsChecked() {
        return isChecked.get();
    }

    public SimpleBooleanProperty isCheckedProperty() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked.set(isChecked);
    }
}
