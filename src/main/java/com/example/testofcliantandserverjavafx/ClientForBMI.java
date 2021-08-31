package com.example.testofcliantandserverjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ClientForBMI extends Application {
    // IO streams
    ObjectOutputStream toServer = null;
    DataInputStream fromServer = null;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Panel p to hold the label and text field
        BorderPane paneForTextField2 = new BorderPane();
        paneForTextField2.setPadding(new Insets(5, 5, 5, 5));
        paneForTextField2.setStyle("-fx-border-color: green");
        paneForTextField2.setLeft(new Label("Enter a radius: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField2.setLeft(tf);

        TextField tf2 = new TextField();
        tf2.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField2.setRight(tf2);

        Button button = new Button();
        paneForTextField2.setCenter(button);

        BorderPane mainPane = new BorderPane();
        // Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextField2);

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("com.example.testofcliantandserverjavafx.Client"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        button.setOnAction(e -> {
            try {
                // Get the radius from the text field
                double weight = Double.parseDouble(tf.getText().trim());
                double height = Double.parseDouble(tf2.getText().trim());
                // Send the radius to the server
                BMIData bmiData = new BMIData(weight,height);
                toServer.writeObject(bmiData);
                toServer.flush();

                // Get area from the server
                String output = fromServer.readUTF();

                // Display to the text area

                ta.appendText(output + '\n');
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        });

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);
            // Socket socket = new Socket("130.254.204.36", 8000);
            // Socket socket = new Socket("drake.Armstrong.edu", 8000);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            ta.appendText(ex.toString() + '\n');
        }
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

