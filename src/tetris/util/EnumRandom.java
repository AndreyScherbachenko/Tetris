/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.util;
import java.util.Random;

/**
 *
 * @author AIS
 */
public class EnumRandom<E extends Enum>{
    private static final Random RND = new Random();
    private final E[] values;
    
    public EnumRandom(Class<E> token) {        
        values = token.getEnumConstants();
    }

    public E random() {
        return values[RND.nextInt(values.length)];
    }
}
