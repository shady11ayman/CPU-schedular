package com.example.os;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RRController implements Initializable {

    @FXML
    protected TextField arrivalField;

    @FXML
    protected TextField burstField;

    @FXML
    protected TextField nameField;
    @FXML
    protected TextArea outputArea;
    @FXML
    private TableView<Process> inputTable;
    @FXML
    private TableColumn<Process, String> procNameCol=new TableColumn<>();

    @FXML
    private TableColumn<Process,Integer> arriveTimeCol=new TableColumn<>();

    @FXML
    private TableColumn<Process, Integer> burstTimeCol=new TableColumn<>();

    private int arrivalTime;
    private int burstTime;
    private String processName;
    ArrayList<Process> myList= new ArrayList<Process>();
    ObservableList<Process> processes = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        procNameCol.setCellValueFactory(new PropertyValueFactory<Process,String>("prosess_name"));
        arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arriving_time"));
        burstTimeCol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("remaining_time"));
    }

    public void goBack(ActionEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("HELOO");
    }
    public void setTextArea(String text) {
        outputArea.appendText(text);
    }

    public void addFCFSProcess(MouseEvent mouseEvent) throws IOException {
        Process p1=new Process(nameField.getText(),
                Integer.parseInt(arrivalField.getText()),
                Integer.parseInt(burstField.getText()),
                0);
        processes.add(p1);
        inputTable.setItems(processes);
        arrivalTime=Integer.parseInt(arrivalField.getText());
        burstTime=Integer.parseInt(burstField.getText());
        processName = nameField.getText();
        myList.add(new Process(processName, arrivalTime, burstTime, 7));
        arrivalField.setText("");
        burstField.setText("");
        nameField.setText("");
    }

    public void swtichToAddQuantum(ActionEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addQuantum.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Add Quantum scene");
    }

    public void doRR(ActionEvent mouseEvent) throws IOException, InterruptedException {

//        Algo a=new Algo();
//        ArrayList<ResultProcess> result=new ArrayList<ResultProcess>();
//        result=a.fcfs(myList);
        FCFS f=new FCFS(myList,false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("processingView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Set the label's text in the new scene
        HelloController controller = loader.getController();
        controller.setTextArea(f.get_top().getProsess_name());
        // Switch to the new scene
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        //to show gant chart live
/*
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    if (fcfs.size!=0) {
                        controller.setTextArea(fcfs.get_top().getProsess_name());
                        fcfs.remove_top_process();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

*/
    }
}
