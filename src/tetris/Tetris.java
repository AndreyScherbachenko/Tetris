/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Andrey
 */
public class Tetris extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Button[][] btnField = new Button[20][10];
        double w = 20, h = 20; 
        for (int i=0;i<20;i++)
            for (int j=0; j<10; j++){
                Button b = new Button();
                b.setPrefSize(w, h);
                btnField[i][j] = b;
                root.getChildren().add(b);
                b.relocate(j*w+j*0.5,i*h+i*4.5);
            }
        
        Scene scene = new Scene(root, 400, 520);
        
        btnField[1][1].setText("*");
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        /*
        TableView table = new TableView();
        for (int i=0; i<10;i++){
            TableColumn<Object,String> tc = new <Object,String>TableColumn("");
            table.getColumns().add(tc);
        }
        
        for (int i=0; i<20;i++){
            TableRow tr = new TableRow();
            table.getItems().add(tr);
        }
        
        for (int i=0;i<20;i++)
            for (int j=0; j<10; j++){
                Item i = new Item();
                table.
            }
        StackPane root = new StackPane();
        root.getChildren().add(table);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
