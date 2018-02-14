/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

/**
 *
 * @author Andrey
 */
enum Orient{
    W,
    N,
    E,
    S;
    
    Orient next(){
        Orient res = this;
        switch (this){
            case W: res = N;
                 break;
            case N: res = E;
                 break;
            case E: res = S;
                 break;
            case S: res = W;
                 break;
        }
        return res;
    }
    
    Orient prev(){
        Orient res = this;
        switch (this){
            case W: res = S;
                 break;
            case S: res = E;
                 break;
            case E: res = N;
                 break;
            case N: res = W;
                 break;
        }
        return res;
    }
    
    
}

enum Block{
    I(Orient.N, 1, 4,
      new int[][]{{1},{1},{1},{1}}    
    ),
    O(null, 4, 4,
      new int[][]{{1,1},{1,1}}
    ),
    J(Orient.N, 2, 3,
      new int[][]{
          {0,1},
          {0,1},
          {1,1}              
      }
    ),
    L(Orient.N, 2, 3,
      new int[][]{
          {1,0},
          {1,0},
          {1,1}
      }
    ),
    S(Orient.W, 3, 2,
      new int[][]{
          {0,1,1},
          {1,1,0}
      }
    ),
    Z(Orient.W, 3, 2,
      new int[][]{
          {1,1,0},
          {0,1,1}
      }
    ),
    T(Orient.W, 3, 2,
      new int[][]{
          {0,1,0},
          {1,1,1}
      }
    );
    
    private Orient[] orientations;
    private Orient orient;
    private int w, h;
    private int[][] mask;
    
    Block(Orient orient, int w, int h, int[][] mask){
        this.orient = orient;
        this.w = w;
        this.h = h;
        this.mask = mask;
    }
    
    Block flipLeft(){
       Block res = this;
       int[][] newmask = new int[this.w][this.h];
       for (int i=0; i<this.w; i++)
           for (int j=0; j<this.h; j++)
               newmask[i][j] = this.mask[j][i];
       orient = orient.prev();
       res.w = h;
       res.h = w;
       res.mask = newmask;
       return res;
    }
    
    Block flipRiht(){
       Block res = this;
       int[][] newmask = new int[this.w][this.h];
       for (int i=0; i<this.w; i++)
           for (int j=0; j<this.h; j++)
               newmask[i][j] = this.mask[j][i];
       orient = orient.prev();
       res.w = h;
       res.h = w;
       res.mask = newmask;
       return res;
    }
}

/*
interface IBlock{
    public boolean flipLeft();
    public boolean flipRight();
    public boolean moveLeft();
    public boolean moveRight();
    public boolean moveDown();
}

abstract class Block implements IBlock {
    private int width = 0;
    private int height = 0;
    private int[][] mask = null;
    
    Block(BlockType type){
    }
    
    public boolean flipLeft(){return false;};
    public boolean flipRight(){return false;};
    public boolean moveLeft(){return false;};
    public boolean moveRight(){return false;};
    public boolean moveDown(){return false;};
    
}
*/

