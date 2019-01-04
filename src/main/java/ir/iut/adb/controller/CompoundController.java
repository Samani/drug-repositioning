package ir.iut.adb.controller;

import ir.iut.adb.neo4j.domain.NeoCompound;
import ir.iut.adb.neo4j.domain.NeoDisease;
import ir.iut.adb.neo4j.repository.NeoCompoundRepository;
import ir.iut.adb.service.DwpcQueryService;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rasool on 12/25/2018.
 * Email: Rasoul.Samani@gmail.com
 */
@RestController
@RequestMapping("/compound")
public class CompoundController {

    @Autowired
    private NeoCompoundRepository neoCompoundRepository;

    @Autowired
    private DwpcQueryService dwpcQueryService;

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("")
    public List<NeoCompound> getCompounds(Pageable pageable) {
        return neoCompoundRepository.findAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public NeoCompound getCompoundById(@PathVariable("id") String id) {
        return neoCompoundRepository.findByIdentifier(id);
    }

    @GetMapping("/similarity")
    public List<NeoDisease> getSimilarityCompound(@RequestParam("abbrev") String abbrev, @RequestParam("identifier") String identifier) throws IOException {
        List<NeoDisease> neoDiseases = new ArrayList<>();
        Integer pathLen = dwpcQueryService.metaPathFromAbbrev(abbrev).size();
        String query = dwpcQueryService.createDWPCQuery(abbrev, identifier, "0.4");
        Session session = sessionFactory.openSession();
        Result result = session.query(query, Collections.emptyMap());
        result.queryResults().forEach(stringObjectMap -> {
            if (stringObjectMap.get("DWPC") != null) {
                NeoDisease disease = (NeoDisease) stringObjectMap.get("n" + pathLen);
                disease.setDwpc((Double) stringObjectMap.get("DWPC"));
                neoDiseases.add(disease);
            }
        });

        return neoDiseases;
    }

    @GetMapping("/search")
    public List<NeoCompound> findCompoundByIdentifier(@RequestParam("identifier") String identifier) {
        return neoCompoundRepository.findByIdentifierLike(identifier);
    }

}
