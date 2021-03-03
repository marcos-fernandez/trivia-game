package net.marcos_fernandez.triviagame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell implements Comparable<Cell> {

    @NotNull
    private int number;

    @NotNull
    private String color;

    @NotNull
    private int positionX;

    @NotNull
    private int positionY;

    @Override
    public int compareTo(final Cell other) {
        return Integer.compare(this.getNumber(), other.getNumber());
    }
}
