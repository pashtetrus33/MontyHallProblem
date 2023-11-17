package org.example.presents;

import org.example.Prizable;

public class Car implements Prizable {
    private final String TYPE = "Car";

    @Override
    public String toString() {
        return "Car";
    }

    @Override
    public String showPresent() {
        return TYPE;
    }
}
