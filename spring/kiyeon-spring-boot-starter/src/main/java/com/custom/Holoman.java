package com.custom;

public class Holoman {
    String name;
    int term;

    public static Holoman create() {
        return new Holoman();
    }

    public Holoman updateName(String name) {
        this.name = name;
        return this;
    }
    public Holoman updateTerm(int term) {
        this.term = term;
        return this;
    }


    @Override
    public String toString() {
        return "Holoman{" +
            "name='" + name + '\'' +
            ", term=" + term +
            '}';
    }
}
