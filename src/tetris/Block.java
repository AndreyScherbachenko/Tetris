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
    _0,
    _90,
    _180,
    _270;
    
    Orient next(){
        Orient res = this;
        switch (this){
            case _0: res = _90;
                 break;
            case _90: res = _180;
                 break;
            case _180: res = _270;
                 break;
            case _270: res = _0;
                 break;
        }
        return res;
    }
    
    Orient prev(){
        Orient res = this;
        switch (this){
            case _0: res = _270;
                 break;
            case _270: res = _180;
                 break;
            case _180: res = _90;
                 break;
            case _90: res = _0;
                 break;
        }
        return res;
    }
    
    
}

enum Block{
    I(Orient._90, 1, 4,
      new int[][]{{1},{1},{1},{1}}    
    ),
    O(null, 4, 4,
      new int[][]{{1,1},{1,1}}
    ),
    J(Orient._90, 2, 3,
      new int[][]{
          {0,1},
          {0,1},
          {1,1}              
      }
    ),
    L(Orient._90, 2, 3,
      new int[][]{
          {1,0},
          {1,0},
          {1,1}
      }
    ),
    S(Orient._0, 3, 2,
      new int[][]{
          {0,1,1},
          {1,1,0}
      }
    ),
    Z(Orient._0, 3, 2,
      new int[][]{
          {1,1,0},
          {0,1,1}
      }
    ),
    T(Orient._0, 3, 2,
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

