package io.github.JFileRenamer.Controller;

import io.github.JFileRenamer.Entity.TableRank;
import io.github.JFileRenamer.Repository.RankRepository;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.util.List;

public class LauchViewController {

    private final RankRepository repository = new RankRepository();

    public void init(){
        fileTable.setItems(repository.getList());

        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        actionColumn.setCellFactory((param) -> {
            TableCell<TableRank,Boolean> cell = new TableCell<TableRank, Boolean>(){
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty == false && item != null){
                        HBox box = new HBox();
                        box.setAlignment(Pos.CENTER);
                        Button button = new Button("Remove");
                        button.setOnMouseClicked(event -> {
//                            this.getTableView().getItems().remove(this.getIndex());
                            repository.getList().remove(this.getIndex());
                        });
                        box.getChildren().add(button);
                        this.setGraphic(box);
                    }else {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                }
            };
            return cell;
        });
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("isChecked"));
        selectColumn.setCellFactory(param -> {
            TableCell<TableRank,Boolean> cell = new TableCell<TableRank, Boolean>(){
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty == false){
                        HBox box = new HBox();
                        box.setAlignment(Pos.CENTER);
                        CheckBox checkBox = new CheckBox();
                        checkBox.setSelected(item);
                        checkBox.setOnMouseClicked((event)->{
                            if(checkBox.isSelected()){
                                repository.getList().get(this.getIndex()).setIsChecked(true);
                            }else{
                                repository.getList().get(this.getIndex()).setIsChecked(false);
                            }
                        });
                        box.getChildren().add(checkBox);
                        this.setGraphic(box);
                    }else {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                }
            };
            return cell;
        });
        fileColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        fileColumn.setCellFactory(new Callback<TableColumn<TableRank, String>, TableCell<TableRank, String>>() {
            @Override
            public TableCell<TableRank, String> call(TableColumn<TableRank, String> param) {
                TableCell<TableRank,String> cell = new TableCell<TableRank, String>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty == false){this.setText(item);}
                        else{ this.setText(null);}
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    private TableColumn<TableRank, Boolean> actionColumn;

    @FXML
    private TableColumn<TableRank, Boolean> selectColumn;

    @FXML
    private TableColumn<TableRank, String> fileColumn;

    @FXML
    private Button replaceButton;

    @FXML
    private TableView<TableRank> fileTable;

    @FXML
    private Button importButton;

    @FXML
    private TextField inputName;

    @FXML
    private CheckBox checkExtension;

    @FXML
    void OnImportFiles(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Files");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        List<File> filelist = fileChooser.showOpenMultipleDialog(new Stage());
        if(filelist != null){repository.addFiles(filelist);}
    }

    @FXML
    void OnReplaceName(MouseEvent event) {
        String name = inputName.getText();
        System.out.println(name + "!");
        if(name != null && !name.equals("") && repository.getList().size()>0){
            repository.renameSelectedFile(name,checkExtension.isSelected());
        }
    }

}

