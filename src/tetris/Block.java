/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;
import java.util.Collection;
import javafx.scene.paint.Color;
import java.util.Random;
/**
 *
 * @author Andrey
 */
class Block {
    public enum Orient{    
        NORTH, EAST, SOUTH, WEST;
    
        Orient turnRight(){
            Orient res = this;
            switch (this){
                case NORTH: res = EAST;
                     break;
                case EAST: res = SOUTH;
                     break;
                case SOUTH: res = WEST;
                     break;
                case WEST: res = NORTH;
                     break;                                                
            }
            return res;
        }
    
        Orient turnLeft(){
            Orient res = this;
            switch (this){
                case NORTH: res = WEST;
                     break;
                case WEST: res = SOUTH;
                     break;
                case SOUTH: res = EAST;
                     break;
                case EAST: res = NORTH;
                     break;                
            }
            return res;
        }        
    }

    public enum Type{
        I, O, J, L, S, Z, T
    }
    
    public enum BlockColor{
        RED(Color.RED), 
        BLUE(Color.BLUE),
        GREEN(Color.GREEN),
        YELLOW(Color.YELLOW), 
        BROWN(Color.BROWN), 
        GRAY(Color.GRAY),
        PINK(Color.PINK);
        
        private Color color;
        
        private static Random random = new Random();
        private static final BlockColor[] VALUES = values();
        private static final int length = VALUES.length;
        
        public static BlockColor random(){            
           return VALUES[random.nextInt(length)]; 
        }
        
        BlockColor(Color color){
            this.color = color;
        }
        
        Color getColor(){
            return this.color;
        }        
    }
           
    private Type type;
    private Orient orient;
    private int width = 0, height = 0;    
    private int x = 0, y = 0;
    private int[][] mask = null;
           
    public BlockColor color = null;
    
    public Block(Type type, Orient orient){
        this.type = type;
        this.orient = orient;
        this.color = BlockColor.random();
        
        switch(type){
            case I:
                switch(orient){
                    case NORTH: case SOUTH:
                        this.width=1; this.height=4;
                        this.mask=new int[][]{{1},{1},{1},{1}}; 
                        break;
                    case EAST: case WEST:
                        this.width=4; this.height=1;
                        this.mask=new int[][]{{1, 1, 1, 1}}; 
                        break;                                        
                }
                break;
            case O:
                switch(orient){
                    case NORTH: case EAST: case SOUTH: case WEST:
                        this.width=2; this.height=2;
                        this.mask=new int[][]{{1,1},{1,1}};
                        break;
                }
                break;        
            case J:
                switch(orient){
                    case NORTH:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{0,1},
                                              {0,1},
                                              {1,1}};
                        break;
                    case EAST:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{1,0,0},
                                              {1,1,1}};
                        break;
                    case SOUTH:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{1,1},
                                              {1,0},
                                              {1,0}};
                        break;
                    case WEST:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{1,1,1},
                                              {0,0,1}};
                }                
                break;            
            case L:
                switch(orient){
                    case NORTH:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{1,0},
                                              {1,0},
                                              {1,1}};
                        break;
                    case EAST:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{1,1,1},
                                              {1,0,0}};
                        break;
                    case SOUTH:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{1,1},
                                              {0,1},
                                              {0,1}};
                        break;
                    case WEST:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{0,0,1},
                                              {1,1,1}};
                }
                break;
            case S:
                switch(orient){
                    case NORTH: case SOUTH:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{0,1,1},
                                              {1,1,0}};
                        break;
                    case EAST: case WEST:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{1,0},
                                              {1,1},
                                              {0,1}};
                        break;                    
                }
                break;    
            case Z:
                switch(orient){
                    case NORTH: case SOUTH:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{1,1,0},
                                              {0,1,1}};
                        break;
                    case EAST: case WEST:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{0,1},
                                              {1,1},
                                              {1,0}};
                        break;
                }
                break;    
            case T:
                switch(orient){
                    case NORTH:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{0,1,0},
                                              {1,1,1}};
                        break;
                    case EAST:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{1,0},
                                              {1,1},
                                              {1,0}};
                        break;
                    case SOUTH:
                        this.width=3; this.height=2;
                        this.mask=new int[][]{{1,1,1},
                                              {0,1,0}};
                        break;
                    case WEST:
                        this.width=2; this.height=3;
                        this.mask=new int[][]{{0,1},
                                              {1,1},
                                              {0,1}};
                }
                break;    
        }            
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int[][] getMask(){
        return this.mask;
    }
        
    public BlockColor getColor(){
        return this.color;
    }
    
    public boolean flipLeft(){return false;};
    public boolean flipRight(){return false;};
    public boolean moveLeft(){return false;};
    public boolean moveRight(){return false;};
    public boolean moveDown(){return false;};
    
}