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
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.os.Algo.myList;

public class HelloController {

    @FXML
    protected TextField arrivalField;

    @FXML
    protected TextField burstField;

    @FXML
    protected TextField nameField;

    @FXML
    protected TextField prioField;
    @FXML
    protected TextArea outputArea;
    @FXML
    protected TextArea timeOutputArea;
    @FXML
    protected TextArea avgWaiting;
    @FXML
    protected TextArea avgTA;
    @FXML
    protected TextField addName;
    @FXML
    protected TextField addBurst;
    @FXML
    protected TextField addPrio;
    @FXML
    protected TextArea procName;
    @FXML
    protected TextArea remTime;


    //private Label outLbl;
    private Stage stage;
    private Scene scene;
    //private Parent root;
    private int arrivalTime;
    private int burstTime;
    private int priority;;
    private String processName;

    private int quantum;
    ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
    ObservableList<Process> processes = FXCollections.observableArrayList();


    public void goBack(ActionEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("HELOO");
        if(myList.size()!=0) {
            myList.clear();
        }
    }

    public void insert(ActionEvent event) {
        burstTime=Integer.parseInt(addBurst.getText());
        priority=Integer.parseInt(addPrio.getText());
        processName = addName.getText();
        Algo.adding=true;
        Algo.globalProcess= new Process(processName,Algo.timer,burstTime,priority);
        addBurst.setText("");
        addName.setText("");
        addPrio.setText("");
    }

    public void switchToScene2(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FCFS.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("HELOO");
    }
    public void swtichToPremPrio(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("premPrio.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Preemative priority scene");
    }
    public void swtichToNonPremPrio(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("noPremPrio.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Non-Preemative priority scene");
    }
    public void switchToNonPremSJF(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("nonPreemSJF.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Non-Preemative SJF scene");
    }
    public void switchToPremSJF(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("preemSJF.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Preemative SJF scene");
    }
    public void swtichToRR(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("RR.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("RR scene scene");
    }

    public void setTextArea(String text) {
        outputArea.appendText(text);
    }
    public void setTimeArea(String text) {
        timeOutputArea.appendText(text);
    }
    public void setAvgWaiting(String text) {
        avgWaiting.appendText(text);
    }
    public void setAvgTA(String text) {
        avgTA.appendText(text);
    }
    public void setProcName(String text) {
        procName.appendText(text +"\n");
    }
    public void setRemTime(String text) {
        remTime.appendText(text +"\n");
    }


}
