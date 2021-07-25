package com.estockmarket.estockmarketapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;
    private long seq;
}
