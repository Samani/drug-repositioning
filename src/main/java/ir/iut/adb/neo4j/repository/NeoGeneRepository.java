package ir.iut.adb.neo4j.repository;

import ir.iut.adb.neo4j.domain.NeoGene;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * Created by rasool on 11/29/2018.
 * Email: Rasoul.Samani@gmail.com
 */
public interface NeoGeneRepository extends Neo4jRepository<NeoGene, Long> {

}
