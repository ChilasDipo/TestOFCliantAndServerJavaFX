package com.example.testofcliantandserverjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientForTimeServer extends Application {
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Panel p to hold the label and text field
        BorderPane paneForTextField2 = new BorderPane();
        paneForTextField2.setPadding(new Insets(5, 5, 5, 5));
        paneForTextField2.setStyle("-fx-border-color: green");
        paneForTextField2.setLeft(new Label("Click for Time: "));

        Button button = new Button();
        button.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField2.setCenter(button);
        button.setMaxSize(80,40);

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

                // Send the radius to the server
                toServer.writeDouble(1);
                toServer.flush();

                // Get area from the server
                String time = fromServer.readUTF();

                // Display to the text area
                ta.appendText("Time is " + time + "\n");
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
            toServer = new DataOutputStream(socket.getOutputStream());
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

