package com.example.os;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static com.example.os.Algo.myList;




public class FCFSController implements Initializable {

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
    private String name;
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

    public void doFCFS(ActionEvent mouseEvent) throws IOException, InterruptedException {

//load output scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstantOutputView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        Algo.live = false;
        // Set the label's text in the new scene
        HelloController controller = loader.getController();

        Algo a = new Algo();
        a.fcfs(myList);
        while (Algo.checker2) {
            if (Algo.checker == false) {

                if(Algo.global_process_Priority_queue.size()==1)
                {
                    name=Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                    controller.setTextArea(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                }
                else if(name!=Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name())
                {
                    name=Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                    controller.setTextArea(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                }
                else {controller.setTextArea("  ");}

                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getRemaining_time());
                System.out.println("------------------------------");
                Algo.checker = true;
            }
        }
    }
    public void doFCFSLive(ActionEvent mouseEvent) throws IOException, InterruptedException {
        //load output scene

        FXMLLoader loader = new FXMLLoader(getClass().getResource("processingView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        // get a reference to the TextArea component in the UI
        TextArea consoleOutputTextArea = (TextArea) loader.getNamespace().get("outputArea");
        TextArea consoleOutputTextAreaTime = (TextArea) loader.getNamespace().get("timeOutputArea");
        TextArea consoleOutputProcName = (TextArea) loader.getNamespace().get("procName");



        // run the algorithm in the background thread
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Algo a = new Algo();
                a.fcfs(myList);
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
            for(Process n:myList) {
                consoleOutputProcName.appendText(n.getProsess_name()+"\n");
            }
            while (Algo.checker2) {
                if (Algo.checker == false) {
                    if(Algo.global_process_Priority_queue.size()==1)
                    {
                        name=Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                        consoleOutputTextArea.appendText(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                    }
                    else if(name!=Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name())
                    {
                        name=Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name();
                        consoleOutputTextArea.appendText(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                    }
                    else {consoleOutputTextArea.appendText("  ");}

                }}
        }).start();
    }
}
