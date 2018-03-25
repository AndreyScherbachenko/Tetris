/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;
import java.util.LinkedList;
/**
 *
 * @author AIS
 */
public class BlockQueue {
  private LinkedList<Block> list; 
  
  public BlockQueue(Block... listOfBlocks){
      this.list = new LinkedList<Block>();
      
      for (Block b:listOfBlocks){
          list.add(b);
      }
  }
  
  public Block getFirst(){
      Block r = list.getFirst();
      list.removeFirst();
      return r;      
  }
  
  public void add2Queue(){
      this.list.add(new Block());
  }
  
  public void add2Queue(Block b){
      this.list.add(b);
  }
  
  public LinkedList<Block> getAll(){
      return new LinkedList<Block>(this.list);
  }
  
  public void resetQueue(){
      list.clear();
      add2Queue();
      add2Queue();
      add2Queue();  
  }
}
