package net.marcos_fernandez.triviagame.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GameBoard extends AbstractDocument {

    private List<Cell> cells;
    private String background;
}
