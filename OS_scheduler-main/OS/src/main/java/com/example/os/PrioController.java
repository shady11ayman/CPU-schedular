package com.example.os;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static com.example.os.Algo.myList;


public class PrioController implements Initializable {

    @FXML
    protected TextField arrivalField;

    @FXML
    protected TextField burstField;

    @FXML
    protected TextField nameField;
    @FXML
    protected TextField priorityField;
    @FXML
    protected TextArea outputArea;

    @FXML
    private TableView<Process> inputTable;
    @FXML
    private TableColumn<Process, String> procNameCol = new TableColumn<>();

    @FXML
    private TableColumn<Process, Integer> arriveTimeCol = new TableColumn<>();

    @FXML
    private TableColumn<Process, Integer> burstTimeCol = new TableColumn<>();

    @FXML
    private TableColumn<Process, Integer> priorityCol = new TableColumn<>();


    @FXML
    private StackedBarChart<String, Number> GanttChart;

    private int arrivalTime;
    private int burstTime;
    private int priority;
    private String processName;
    private String name;
    int count = 0;
    ObservableList<Process> processes = FXCollections.observableArrayList();
    public ArrayList<Process> result = new ArrayList<Process>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        procNameCol.setCellValueFactory(new PropertyValueFactory<Process, String>("prosess_name"));
        arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Process, Integer>("arriving_time"));
        burstTimeCol.setCellValueFactory(new PropertyValueFactory<Process, Integer>("remaining_time"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<Process, Integer>("priority"));

    }

    public void drawChart() {
        Integer burst = 5;
        String seriesName = "New Series";
        XYChart.Series<String, Number> newSeries = new XYChart.Series<>();
        newSeries.setName(seriesName);
        newSeries.getData().add(new XYChart.Data<>("Processes", burst));
        GanttChart.getData().add(newSeries);
    }

    public void goBack(ActionEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("HELOO");
    }


    public void addPrio(ActionEvent mouseEvent) throws IOException {
        //table update
        Process p1 = new Process(nameField.getText(),
                Integer.parseInt(arrivalField.getText()),
                Integer.parseInt(burstField.getText()), Integer.parseInt(priorityField.getText()));
        processes.add(p1);
        inputTable.setItems(processes);

        //add inout to myList
        arrivalTime = Integer.parseInt(arrivalField.getText());
        burstTime = Integer.parseInt(burstField.getText());
        priority = Integer.parseInt(priorityField.getText());
        processName = nameField.getText();
        myList.add(new Process(processName, arrivalTime, burstTime, priority));
        arrivalField.setText("");
        burstField.setText("");
        nameField.setText("");
        priorityField.setText("");
    }

    public void doPreemPrioLive(ActionEvent mouseEvent) throws IOException {
//load output scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("processingView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        // get a reference to the TextArea component in the UI
        TextArea consoleOutputTextArea = (TextArea) loader.getNamespace().get("outputArea");
        TextArea consoleOutputTextAreaTime = (TextArea) loader.getNamespace().get("timeOutputArea");
        TextArea consoleOutputProcName = (TextArea) loader.getNamespace().get("procName");
        TextArea consoleOutputremTime = (TextArea) loader.getNamespace().get("remTime");


        Algo.live = true;
        Algo.isPriority = true;
        // run the algorithm in the background thread
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Algo a = new Algo();
                a.priority_preem(myList);
                while (Algo.checker2) {
                    if (Algo.checker == false) {
                        // write console output to the stream
                        System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                        System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getRemaining_time());
                        System.out.println("------------------------------");
                        Algo.checker = true;
                    }
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        for (Process n : myList) {
            consoleOutputProcName.appendText(n.getProsess_name() + "\n");
            consoleOutputremTime.appendText(Integer.toString(n.getRemaining_time()) + "\n");
        }
        // read from the console output stream and update the TextArea in the UI
        new Thread(() -> {
            //HelloController controller=new HelloController();

            while (Algo.checker2) {

                if (Algo.checker == false) {
                    consoleOutputProcName.setText("");
                    consoleOutputremTime.setText("");
                    for (Process n : myList) {

                        if(n.getRemaining_time()==0) {
                            consoleOutputProcName.appendText(n.getProsess_name() + "\n");
                            consoleOutputremTime.appendText(Integer.toString(n.getRemaining_time()) + "\n");
                        }
                        else{
                            consoleOutputProcName.appendText(n.getProsess_name() + "\n");
                            consoleOutputremTime.appendText(Integer.toString(n.getRemaining_time()+1) + "\n");
                        }
                    }
                    if (Algo.global_process_Priority_queue.size() == 1) {
                        name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                        consoleOutputTextArea.appendText(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                    } else if (name != Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name()) {
                        name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                        consoleOutputTextArea.appendText(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                    } else {
                        consoleOutputTextArea.appendText("_");
                    }
                }
            }

        }).start();


    }

    public void doPreemPrio(ActionEvent mouseEvent) throws IOException {  //not live
        //load output scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstantOutputView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        Algo.live = false;
        Algo.isPriority = true;
        // Set the label's text in the new scene
        HelloController controller = loader.getController();

        Algo a = new Algo();
        a.priority_preem(myList);
        while (Algo.checker2) {
            if (Algo.checker == false) {

                if (Algo.global_process_Priority_queue.size() == 1) {
                    name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                    controller.setTextArea(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                } else if (name != Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name()) {
                    name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                    controller.setTextArea(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                } else {
                    controller.setTextArea("_");
                }

                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getRemaining_time());
                System.out.println("------------------------------");
                Algo.checker = true;
            }
        }

    }

    public void doNonPreemPrio(ActionEvent mouseEvent) throws IOException {
        //load output scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstantOutputView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        Algo.live = false;
        Algo.isPriority = true;
        // Set the label's text in the new scene
        HelloController controller = loader.getController();

        Algo a = new Algo();
        a.priority(myList);
        while (Algo.checker2) {
            if (Algo.checker == false) {

                if (Algo.global_process_Priority_queue.size() == 1) {
                    name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                    controller.setTextArea(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                } else if (name != Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name()) {
                    name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                    controller.setTextArea(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                } else {
                    controller.setTextArea("_");
                }

                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getRemaining_time());
                System.out.println("------------------------------");
                Algo.checker = true;
            }
        }
    }

    public void doNonPreemPrioLive(ActionEvent mouseEvent) throws IOException {

        //load output scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("processingView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        // get a reference to the TextArea component in the UI
        TextArea consoleOutputTextArea = (TextArea) loader.getNamespace().get("outputArea");
        TextArea consoleOutputTextAreaTime = (TextArea) loader.getNamespace().get("timeOutputArea");
        TextArea consoleOutputProcName = (TextArea) loader.getNamespace().get("procName");
        Algo.live = true;
        Algo.isPriority = true;


        // run the algorithm in the background thread
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Algo a = new Algo();
                a.priority(myList);
                while (Algo.checker2) {
                    if (Algo.checker == false) {
                        // write console output to the stream
                        System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                        System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getRemaining_time());
                        System.out.println("------------------------------");
                        Algo.checker = true;
                    }
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        // read from the console output stream and update the TextArea in the UI
        new Thread(() -> {
            //HelloController controller=new HelloController();
            for (Process n : myList) {
                consoleOutputProcName.appendText(n.getProsess_name() + "\n");

            }
            while (Algo.checker2) {
                if (Algo.checker == false) {
                    if (Algo.global_process_Priority_queue.size() == 1) {
                        name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                        consoleOutputTextArea.appendText(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                    } else if (name != Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name()) {
                        name = Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                        consoleOutputTextArea.appendText(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                    } else {
                        consoleOutputTextArea.appendText("_");
                    }

                }
            }
        }).start();
    }
}




