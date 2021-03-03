package net.marcos_fernandez.triviagame.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Question extends AbstractDocument {

    @NotNull
    private String text;

    @NotNull
    private Set<Answer> answers;

    @NotNull
    private Level level;

}
