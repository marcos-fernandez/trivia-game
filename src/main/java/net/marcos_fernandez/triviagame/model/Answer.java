package net.marcos_fernandez.triviagame.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Answer {

    @NotNull
    @EqualsAndHashCode.Include
    private String text;
    private boolean correct;
}
