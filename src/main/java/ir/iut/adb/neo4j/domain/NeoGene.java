package ir.iut.adb.neo4j.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasool on 11/29/2018.
 * Email: Rasoul.Samani@gmail.com
 */
@Setter
@Getter
@NodeEntity("Gene")
public class NeoGene {

    @Id
    @GeneratedValue
    private Long id;
    private Long identifier;
    private String name;
    private String chromosome;
    private String description;
    private String url;

    @Relationship(type = "COVARIES_GcG", direction = Relationship.OUTGOING)
    private List<NeoGene> genes = new ArrayList<>();
}
