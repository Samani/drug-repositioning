package ir.iut.adb.neo4j.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasool on 11/29/2018.
 * Email: Rasoul.Samani@gmail.com
 */
@Setter
@Getter
@NodeEntity("Disease")
public class NeoDisease {

    @Id
    @GeneratedValue
    private Long id;
    private String identifier;
    private String name;
    private String source;
    private String url;
    @Transient
    private Double dwpc;

    @Relationship(type = "ASSOCIATES_DaG", direction = Relationship.OUTGOING)
    private List<NeoGene> associatesGene = new ArrayList<>();

    @Relationship(type = "DOWNREGULATES_DdG", direction = Relationship.OUTGOING)
    private List<NeoGene> downRegulateGene = new ArrayList<>();

    @Relationship(type = "UPREGULATES_DuG", direction = Relationship.OUTGOING)
    private List<NeoGene> upRegulateGene = new ArrayList<>();

    @Relationship(type = "PRESENTS_DpS", direction = Relationship.OUTGOING)
    private List<NeoSymptom> symptoms = new ArrayList<>();
}
