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
    private final int cellRows = 20, cellColumns = 10;
    private final Button[][] btnField = new Button[cellRows][cellColumns];
    private final int cellHintRows = 3*4, cellHintColumns = 4;
    private final Button[][] btnHintField = new Button[cellHintRows][cellHintColumns];
    private final Background background = new Background(new BackgroundFill(Color.web("#BBB"), CornerRadii.EMPTY, Insets.EMPTY));
   
    @Override
    public void start(Stage primaryStage) {
        
        double cellWidth = 20, cellHeight = 20;
                
        double sceneHeight = 520;
        double leftPaneWidth = (cellWidth*cellColumns+cellWidth*0.5);
        double rightPaneWidth = 200;
        double rightTopPaneHeight = (cellHeight*cellHintRows+cellHeight*3.5);
        double rightBottomPaneHeight = sceneHeight-rightTopPaneHeight;
        double sceneWidth = leftPaneWidth+rightPaneWidth;        
        
        Pane root = new Pane();
        Pane leftPane = new Pane();
        Pane rightPane = new Pane();
        Pane rightTopPane = new Pane();
        VBox rightBottomPane = new VBox();
        
        
        leftPane.setBackground(new Background(new BackgroundFill(Color.web("#DDF"), CornerRadii.EMPTY, Insets.EMPTY)));
        rightPane.setBackground(new Background(new BackgroundFill(Color.web("#DDF"), CornerRadii.EMPTY, Insets.EMPTY)));
        rightTopPane.setBackground(new Background(new BackgroundFill(Color.web("#DDF"), CornerRadii.EMPTY, Insets.EMPTY)));
        rightBottomPane.setBackground(new Background(new BackgroundFill(Color.web("#BBF"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        leftPane.setPrefSize(leftPaneWidth, sceneHeight);
        leftPane.relocate(0,0);
        
        rightPane.setPrefSize(rightPaneWidth, sceneHeight);
        rightPane.relocate(leftPaneWidth,0);
        
        rightTopPane.setPrefSize(rightPaneWidth, rightTopPaneHeight);
        rightTopPane.relocate(0,0);        
        
        rightBottomPane.setPrefSize(rightPaneWidth, rightBottomPaneHeight);
        rightBottomPane.relocate(0,rightTopPaneHeight);
        rightBottomPane.setAlignment(Pos.CENTER);
        
        rightPane.getChildren().addAll(rightTopPane,rightBottomPane);
               
        for (int i=0;i<cellRows;i++)
            for (int j=0; j<cellColumns; j++){
                Button b = new Button();
                b.setPrefSize(cellWidth, cellHeight);
                b.setBackground(background);
                btnField[i][j] = b;           
                b.relocate(j*cellWidth+j*0.5,i*cellHeight+i*5.5);
                leftPane.getChildren().add(b);                
            }                
        
        for (int i=0;i<cellHintRows;i++)
            for (int j=0; j<cellHintColumns; j++){
                Button b = new Button();
                b.setPrefSize(cellWidth, cellHeight);
                b.setBackground(background);
                btnHintField[i][j] = b;           
                b.relocate(j*cellWidth+j*0.5,i*cellHeight+i*5.5);
                rightTopPane.getChildren().add(b);                
            }  
        
        for (Block.Type b:Block.Type.values()){
            listOfBlock.getItems().add(b);
        }
        listOfBlock.setOnAction(this);
                
        for (Block.Orient o:Block.Orient.values()){
            listOfOrient.getItems().add(o);
        }       
        listOfOrient.setOnAction(this);
        rightBottomPane.getChildren().addAll(listOfBlock, listOfOrient);
                                       
        root.getChildren().addAll(leftPane, rightPane);                
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
                        
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
        if (type != null && orient != null) {            
            block = new Block(type, orient);
        
            for (int i=0; i<this.cellRows; i++)
                for (int j=0;j<this.cellColumns;j++)
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
