/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.util.List;


/**
 *
 * @author Andrey
 */
public class Tetris extends Application implements EventHandler<ActionEvent> {
    
    private ComboBox<Block.Type> listOfBlock = new ComboBox<Block.Type>();        
    private ComboBox<Block.Orient> listOfOrient = new ComboBox<Block.Orient>();
    private Block block = null;
    private Button[][] btnField = new Button[20][10];
    private int w = 10, h = 20; 
    private Background background;
   
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Pane leftPane = new Pane();
        VBox rightPane = new VBox();
        rightPane.setAlignment(Pos.CENTER);        
        background = new Background(new BackgroundFill(Color.web("#BBB"), CornerRadii.EMPTY, Insets.EMPTY));        
        
        double h = 20, w = 20;
        for (int i=0;i<this.h;i++)
            for (int j=0; j<this.w; j++){
                Button b = new Button();
                b.setPrefSize(w, h);
                b.setBackground(background);
                btnField[i][j] = b;                
                leftPane.getChildren().add(b);
                b.relocate(j*w+j*0.5,i*h+i*5.5);
            }        
                
        for (Block.Type b:Block.Type.values()){
            listOfBlock.getItems().add(b);
        }
        listOfBlock.setOnAction(this);
                
        for (Block.Orient o:Block.Orient.values()){
            listOfOrient.getItems().add(o);
        }       
        listOfOrient.setOnAction(this);
        rightPane.getChildren().addAll(listOfBlock, listOfOrient);
                        
        h = 520;        
        int leftWidth = (int)(w*10+w*0.5);
        leftPane.setBackground(new Background(new BackgroundFill(Color.web("#DDF"), CornerRadii.EMPTY, Insets.EMPTY)));
        leftPane.setPrefSize(leftWidth,h);
        leftPane.relocate(0,0);          
        
        
        
        int rightWidth = 200; 
        rightPane.setBackground(new Background(new BackgroundFill(Color.web("#EFE"), CornerRadii.EMPTY, Insets.EMPTY)));
        rightPane.setPrefSize(rightWidth, h);
        rightPane.relocate(leftWidth,0);
        root.getChildren().add(leftPane);
        root.getChildren().add(rightPane);
        
        Scene scene = new Scene(root, 400, h);
                        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void handle(ActionEvent e){
        if  (e.getSource()== listOfBlock){
            
        } else if (e.getSource()== listOfOrient){
            
        }
        
        Block.Type type = listOfBlock.getValue();
        Block.Orient orient = listOfOrient.getValue();
        System.out.println(type+":"+orient);
        if (type != null && orient != null){            
        block = new Block(type, orient);
        
        for (int i=0; i<this.h; i++)
            for (int j=0;j<this.w;j++)
                btnField[i][j].setBackground(background);
               
        int w = block.getWidth(); 
        int h = block.getHeight();
        int[][] mask = block.getMask();
        for (int i=0; i<h; i++)
            for (int j=0;j<w;j++)
                if (mask[i][j] == 1) {
                    btnField[i][j].setBackground(new Background(new BackgroundFill(block.color.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
                }
        }
    }
}
