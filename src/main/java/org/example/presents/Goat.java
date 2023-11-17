package org.example.presents;

import org.example.Prizable;

public class Goat implements Prizable {
    private final String TYPE = "Goat";
    @Override
    public String toString() {
        return "Goat";
    }

    @Override
    public String showPresent() {
        return TYPE;
    }
}
