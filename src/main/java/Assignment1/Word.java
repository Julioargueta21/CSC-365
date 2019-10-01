package Assignment1;

import java.io.Serializable;

public class Word implements Serializable {

    public String word;
    public double weight;

    public Word(String word, double weight) {
        this.word = word;
        this.weight = weight;


    }

}