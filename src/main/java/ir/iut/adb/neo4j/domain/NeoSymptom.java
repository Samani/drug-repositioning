package ir.iut.adb.neo4j.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by rasool on 11/29/2018.
 * Email: Rasoul.Samani@gmail.com
 */
@Setter
@Getter
@NodeEntity("Symptom")
public class NeoSymptom {

    @Id
    @GeneratedValue
    private Long id;
    private String identifier;
    private String name;
    private String source;
    private String url;
}
