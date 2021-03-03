package net.marcos_fernandez.triviagame.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Level extends AbstractDocument {

    @NotNull
    private String description;

}

