package org.example;

public class Door {
    private Prizable prize;
    private int index;

    public Door(int index, Prizable present) {
        this.index = index;
        this.prize = present;
    }

    public Prizable getPrize() {
        return prize;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {

        return "Door" + index + ": " + prize;
    }
}
