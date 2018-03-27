/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import tetris.util.*;
import javafx.scene.layout.Pane;

/**
 *
 * @author Andrey
 */
public class Block {

    public enum Orient {
        NORTH, EAST, SOUTH, WEST;

        private static final EnumRandom<Orient> RND = new EnumRandom<Orient>(Orient.class);

        Orient turnRight() {
            Orient res = this;
            switch (this) {
                case NORTH:
                    res = EAST;
                    break;
                case EAST:
                    res = SOUTH;
                    break;
                case SOUTH:
                    res = WEST;
                    break;
                case WEST:
                    res = NORTH;
                    break;
            }
            return res;
        }

        Orient turnLeft() {
            Orient res = this;
            switch (this) {
                case NORTH:
                    res = WEST;
                    break;
                case WEST:
                    res = SOUTH;
                    break;
                case SOUTH:
                    res = EAST;
                    break;
                case EAST:
                    res = NORTH;
                    break;
            }
            return res;
        }

        public static Orient random() {
            return RND.random();
        }
    }

    public enum Type {
        I, O, J, L, S, Z, T;

        private static final EnumRandom<Type> RND = new EnumRandom<Type>(Type.class);

        public static Type random() {
            return RND.random();
        }
    }

    public enum BlockColor {
        RED(Color.RED),
        BLUE(Color.BLUE),
        GREEN(Color.GREEN),
        YELLOW(Color.YELLOW),
        BROWN(Color.BROWN),
        GRAY(Color.GRAY),
        PINK(Color.SANDYBROWN);

        private final Color color;

        private static final EnumRandom<BlockColor> RND = new EnumRandom<BlockColor>(BlockColor.class);

        public static BlockColor random() {
            return RND.random();
        }

        BlockColor(Color color) {
            this.color = color;
        }

        Color getColor() {
            return this.color;
        }
    }

    private Type type;
    private Orient orient;
    private int width = 0, height = 0;
    private int[][] mask = null;
    private BlockColor color = null;

    public Block() {
        this(Block.Type.random(), Block.Orient.random(), Block.BlockColor.random());
    }

    public Block(Type type, Orient orient, BlockColor color) {
        this.type = type;
        this.orient = orient;
        this.color = color;

        switch (type) {
            case I:
                switch (orient) {
                    case NORTH:
                    case SOUTH:
                        this.width = 1;
                        this.height = 4;
                        this.mask = new int[][]{{1}, {1}, {1}, {1}};
                        break;
                    case EAST:
                    case WEST:
                        this.width = 4;
                        this.height = 1;
                        this.mask = new int[][]{{1, 1, 1, 1}};
                        break;
                }
                break;
            case O:
                switch (orient) {
                    case NORTH:
                    case EAST:
                    case SOUTH:
                    case WEST:
                        this.width = 2;
                        this.height = 2;
                        this.mask = new int[][]{{1, 1}, {1, 1}};
                        break;
                }
                break;
            case J:
                switch (orient) {
                    case NORTH:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{0, 1},
                        {0, 1},
                        {1, 1}};
                        break;
                    case EAST:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{1, 0, 0},
                        {1, 1, 1}};
                        break;
                    case SOUTH:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{1, 1},
                        {1, 0},
                        {1, 0}};
                        break;
                    case WEST:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{1, 1, 1},
                        {0, 0, 1}};
                }
                break;
            case L:
                switch (orient) {
                    case NORTH:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{1, 0},
                        {1, 0},
                        {1, 1}};
                        break;
                    case EAST:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{1, 1, 1},
                        {1, 0, 0}};
                        break;
                    case SOUTH:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{1, 1},
                        {0, 1},
                        {0, 1}};
                        break;
                    case WEST:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{0, 0, 1},
                        {1, 1, 1}};
                }
                break;
            case S:
                switch (orient) {
                    case NORTH:
                    case SOUTH:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{0, 1, 1},
                        {1, 1, 0}};
                        break;
                    case EAST:
                    case WEST:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{1, 0},
                        {1, 1},
                        {0, 1}};
                        break;
                }
                break;
            case Z:
                switch (orient) {
                    case NORTH:
                    case SOUTH:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{1, 1, 0},
                        {0, 1, 1}};
                        break;
                    case EAST:
                    case WEST:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{0, 1},
                        {1, 1},
                        {1, 0}};
                        break;
                }
                break;
            case T:
                switch (orient) {
                    case NORTH:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{0, 1, 0},
                        {1, 1, 1}};
                        break;
                    case EAST:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{1, 0},
                        {1, 1},
                        {1, 0}};
                        break;
                    case SOUTH:
                        this.width = 3;
                        this.height = 2;
                        this.mask = new int[][]{{1, 1, 1},
                        {0, 1, 0}};
                        break;
                    case WEST:
                        this.width = 2;
                        this.height = 3;
                        this.mask = new int[][]{{0, 1},
                        {1, 1},
                        {0, 1}};
                }
                break;
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int[][] getMask() {
        return this.mask;
    }

    public Color getColor() {
        return this.color.color;
    }

    public void draw(Pane[][] field, int offsetX, int offsetY) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.mask[i][j] == 1) {
                    field[offsetY + i][offsetX + j].setBackground(new Background(new BackgroundFill(this.color.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    public boolean flipLeft() {
        return false;
    }

    ;
    public boolean flipRight() {
        return false;
    }

    ;
    public boolean moveLeft() {
        return false;
    }

    ;
    public boolean moveRight() {
        return false;
    }

    ;
    public boolean moveDown() {
        return false;
    }
;

}
