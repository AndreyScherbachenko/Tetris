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

    public BlockQueue(Block... listOfBlocks) {
        this.list = new LinkedList<Block>();

        for (Block b : listOfBlocks) {
            list.add(b);
        }
    }

    public void add2Queue() {
        this.list.add(new Block());
    }

    public void add2Queue(Block b) {
        this.list.add(b);
    }

    public LinkedList<Block> getAll() {
        return new LinkedList<Block>(this.list);
    }
    
    public Block getFirstAndPutRandomLast() {
        Block r = list.getLast();
        list.removeLast();
        list.addFirst(new Block());
        return r;
    }

    public int getQueueHeight(){
        int h = 0;
        for (Block b:this.list)
            h+=b.getHeight();
        return h;
    }
    
    public int getQueueHeightWithDelimiter(){        
        return this.getQueueHeight()-1+this.list.size();
    }
    
    public void resetQueue() {
        list.clear();
        add2Queue();
        add2Queue();
        add2Queue();
    }
}
