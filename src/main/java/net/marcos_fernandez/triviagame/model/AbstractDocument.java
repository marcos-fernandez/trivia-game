package net.marcos_fernandez.triviagame.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@Data
public abstract class AbstractDocument {

    @Id
    private String id;

    @Version
    private Long version;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastUpdate;
}
