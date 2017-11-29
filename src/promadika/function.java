/*
 * Muhammad Faisal Amir
 * f.miir117@gmail.com
 * id.amirisback.bandung
 * Copyright 2017
 */
package promadika;

import java.util.Random;

/**
 *
 * @author Faisal Amir
 */
public class function {
    
    
    //Deklarasi variable unuk random--------------------------------------------
    private char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private StringBuilder stringBuilder = new StringBuilder();
    private Random random = new Random();
    private String output;
    //--------------------------------------------------------------------------

    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    //Method membuat random char------------------------------------------------
    public String getRandomChar() {
        for (int lenght = 0; lenght < 5; lenght++) {
            //Panjang Karakter yang akan di random bisa di rubah di perulangan for
            Character character = chars[random.nextInt(chars.length)];
            stringBuilder.append(character);
        }
        output = stringBuilder.toString();
        stringBuilder.delete(0, 5);
        return output;
    }
    //--------------------------------------------------------------------------
    
    
    
}
