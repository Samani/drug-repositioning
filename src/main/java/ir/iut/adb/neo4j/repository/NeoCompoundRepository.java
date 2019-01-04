package ir.iut.adb.neo4j.repository;

import ir.iut.adb.neo4j.domain.NeoCompound;
import ir.iut.adb.neo4j.domain.NeoDisease;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by rasool on 11/29/2018.
 * Email: Rasoul.Samani@gmail.com
 */
public interface NeoCompoundRepository extends Neo4jRepository<NeoCompound, Long> {

    NeoCompound findByIdentifier(@Param("identifier") String identifier);

    List<NeoCompound> findByIdentifierLike(@Param("identifier") String identifier);

    @Query("MATCH (c:Compound)-[r:TREATS_CtD]-(d:Disease) Where c.identifier = {identifier} RETURN d")
    List<NeoDisease> getDiseaseRelByCompound(@Param("identifier") String identifier);

}
