package ir.iut.adb.neo4j.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasool on 11/27/2018.
 * Email: Rasoul.Samani@gmail.com
 */
@NoArgsConstructor
@Setter
@Getter
@NodeEntity("Compound")
public class NeoCompound {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String identifier;
    private String description;
    private String url;
    private String inchikey;
    private String inchi;
    private String source;

    @Relationship(type = "DRUG_SIMILAR_DRUG", direction = Relationship.OUTGOING)
    private List<NeoCompound> compounds = new ArrayList<>();

    @Relationship(type = "TREATS_CtD", direction = Relationship.OUTGOING)
    private List<NeoDisease> diseases = new ArrayList<>();

    @Relationship(type = "PALLIATES_CpD", direction = Relationship.OUTGOING)
    private List<NeoDisease> diseasesPalliate = new ArrayList<>();

    @Relationship(type = "BINDS_CbG", direction = Relationship.OUTGOING)
    private List<NeoGene> bindsGene = new ArrayList<>();

    @Relationship(type = "CAUSES_CcSE", direction = Relationship.OUTGOING)
    private List<NeoSideEffect> sideEffects = new ArrayList<>();
}
