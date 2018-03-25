/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.application.Application;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 *
 * @author Andrey
 */
public class Tetris extends Application implements EventHandler<ActionEvent> {
    
    private final int CELL_ROWS = 20, CELL_COLUMNS = 10;
    private final int CELL_HINT_ROWS = 3+3*4, CELL_HINT_COLUMNS = 4;
    private final Background background = new Background(new BackgroundFill(Color.web("#CCC"), CornerRadii.EMPTY, Insets.EMPTY));
    
    private ComboBox<Block.Type> listOfBlock = new ComboBox<Block.Type>();        
    private ComboBox<Block.Orient> listOfOrient = new ComboBox<Block.Orient>();
    private Button refreshBlockQueue = new Button();
    private Block block = null;    
    
    private final Pane[][] field = new Pane[CELL_ROWS][CELL_COLUMNS];
    
    private final Pane[][] hintField = new Pane[CELL_HINT_ROWS][CELL_HINT_COLUMNS];
    
   
    private BlockQueue blockQueue = new BlockQueue();
    
    @Override
    public void start(Stage primaryStage) {
        
        double cellWidth = 20, cellHeight = 20;
        double cellHintWidth = 10, cellHintHeight = 10;
                
        double sceneHeight = CELL_ROWS*cellHeight+cellHeight*1;
        double leftPaneWidth = (cellWidth*CELL_COLUMNS+cellWidth*0.5);
        double rightPaneWidth = 200;
        double rightTopPaneHeight = (cellHintHeight*CELL_HINT_ROWS+cellHintHeight*1.5);
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
               
        for (int i=0;i<CELL_ROWS;i++)
            for (int j=0; j<CELL_COLUMNS; j++){
                Pane p = new Pane();
                p.setPrefSize(cellWidth, cellHeight);
                p.setBackground(background);
                field[i][j] = p;           
                p.relocate(j*cellWidth+j*1,i*cellHeight+i*1);
                leftPane.getChildren().add(p);                
            }                
        
        for (int i=0;i<CELL_HINT_ROWS;i++)
            for (int j=0; j<CELL_HINT_COLUMNS; j++){
                Pane p = new Pane();
                p.setPrefSize(cellHintWidth, cellHintHeight);
                p.setBackground(background);
                hintField[i][j] = p;           
                p.relocate(j*cellHintWidth+j*1,i*cellHintHeight+i*1);
                rightTopPane.getChildren().add(p);
            }  
        
        refreshBlockQueue.setText("Refresh blocks queue");
        refreshBlockQueue.setOnAction(this);
        
        for (Block.Type b:Block.Type.values()){
            listOfBlock.getItems().add(b);
        }
        listOfBlock.setOnAction(this);
                
        for (Block.Orient o:Block.Orient.values()){
            listOfOrient.getItems().add(o);
        }       
        listOfOrient.setOnAction(this);
        rightBottomPane.getChildren().addAll(refreshBlockQueue, listOfBlock, listOfOrient);
                                       
        root.getChildren().addAll(leftPane, rightPane);                
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
                        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
        tetrisStart();
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
                            
        Block.Type type = listOfBlock.getValue();
        Block.Orient orient = listOfOrient.getValue();
        System.out.println(type+":"+orient);
        if (type != null && orient != null) {            
            block = new Block(type, orient, Block.BlockColor.random());
        
            for (int i=0; i<this.CELL_ROWS; i++)
                for (int j=0;j<this.CELL_COLUMNS;j++)
                    field[i][j].setBackground(background);
               
            int w = block.getWidth(); 
            int h = block.getHeight();
            int[][] mask = block.getMask();
            
            for (int i=0; i<h; i++)
                for (int j=0;j<w;j++)
                    if (mask[i][j] == 1) {
                        field[i][j].setBackground(new Background(new BackgroundFill(block.color.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
                    }
        } 
       } else if (e.getSource()== refreshBlockQueue){
            this.blockQueue.resetQueue();                
            showBlockQueue();
        }
    }
    
    private void tetrisStart(){
        this.blockQueue.resetQueue();                
        showBlockQueue();
    }
    
    private void showBlockQueue(){
        for (int i=0; i<this.CELL_HINT_ROWS; i++)
            for (int j=0;j<this.CELL_HINT_COLUMNS;j++)
                hintField[i][j].setBackground(background);
        
        int offsetX = 0, offsetY = 0;
        for(Block b:this.blockQueue.getAll())
            offsetY+=b.getHeight()+1;
        
        offsetY = Math.round(CELL_HINT_ROWS/2- offsetY/2);                        
        
        for(Block b:this.blockQueue.getAll()){
            offsetX = Math.round(CELL_HINT_COLUMNS/2- b.getWidth()/2);                        
            b.draw(hintField, offsetX, offsetY);            
            offsetY+=b.getHeight()+1;
        }
    } 
}
