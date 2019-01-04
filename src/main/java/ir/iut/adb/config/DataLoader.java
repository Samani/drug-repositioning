package ir.iut.adb.config;

import ir.iut.adb.neo4j.domain.NeoCompound;
import ir.iut.adb.neo4j.repository.NeoCompoundRepository;
import ir.iut.adb.neo4j.repository.NeoGeneRepository;
import ir.iut.adb.service.DwpcQueryService;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rasool on 12/16/2016.
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private NeoGeneRepository neoGeneRepository;

    @Autowired
    private NeoCompoundRepository neoCompoundRepository;

    @Autowired
    private DwpcQueryService dwpcQueryService;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        try (Stream<String> stream = Files.lines(Paths.get(new ClassPathResource("drugbank-targets.txt").getURI()))) {
//            stream.forEach(new Consumer<String>() {
//                @Override
//                public void accept(String s) {
//                    String tId = s.split("\\t")[0];
//                    String tName = s.split("\\t")[1];
//
//                    NeoGene neoTarget = new NeoGene();
//                    neoTarget.setId(tId);
//                    neoTarget.setName(tName);
//                    neoGeneRepository.save(neoTarget);
//
////                    System.out.println(tId + "  " + tName);
//                }
//            });
//        }
//
////        System.out.println(neoGeneRepository.findById("BE0000767").get().getName());
//
//
//        try (Stream<String> stream = Files.lines(Paths.get(new ClassPathResource("drugbank-drugs.txt").getURI()))) {
//            stream.forEach(s -> {
//                String tId = s.split("\\t")[0];
//                String tName = s.split("\\t")[1];
//
//                NeoCompound neoDrug = new NeoCompound();
//                neoDrug.setId(tId);
//                neoDrug.setName(tName);
//
//                if (s.split("\\t").length > 2) {
//                    String tRest = s.split("\\t")[2];
//                    neoDrug.setRestriction(tRest);
//                }
//                if (s.split("\\t").length > 3) {
//                    String tDesc = s.split("\\t")[3];
//                    neoDrug.setDescription(tDesc);
//                }
//
//                if (!neoCompoundRepository.findById(neoDrug.getId()).isPresent())
//                    neoCompoundRepository.save(neoDrug);
//
////                    System.out.println(tId + "  " + tName);
//            });
//        }
//
//
//        try (Stream<String> stream = Files.lines(Paths.get(new ClassPathResource("drugbank-drug_target.txt").getURI()))) {
//            stream.forEach(s -> {
//                String dId = s.split("\\t")[0];
//                String tId = s.split("\\t")[1];
//
//                NeoCompound drug = neoCompoundRepository.findById(dId).get();
//                NeoGene gene = neoGeneRepository.findById(tId).get();
//
//                System.out.println(drug.getName() + "  " + gene.getName());
//
//                NeoTarget drugTarget = new NeoTarget();
//                drugTarget.setDrug(drug);
//                drugTarget.setGene(gene);
//                neoTargetRepository.save(drugTarget);
//
//                drug.addTarget(drugTarget);
//                neoCompoundRepository.save(drug);
//
//                gene.addDrug(drug);
//                neoGeneRepository.save(gene);
//
////                    System.out.println(tId + "  " + tName);
//            });
//        }

//        System.out.println(neoCompoundRepository.findById("DB00002").get().getName());
//        System.out.println(neoCompoundRepository.findById("DB00002").get().getTargets().size());
//        neoCompoundRepository.findById("DB00002").get().getTargets().stream().forEach(t -> System.out.println(t.getGene().getName()));
//        System.out.println(neoGeneRepository.findById("BE0002096").get().getDrugs().size());

//        NeoCompound drug = neoCompoundRepository.findById("DB00002").get();
//        System.out.println(drug.getTargets().size());
//        NeoGene target = neoGeneRepository.findById("BE0002096").get();
//        System.out.println(target.getDrugs().size());

//        NeoTarget drugTarget = new NeoTarget();
//        drugTarget.setDrug(drug);
//        drugTarget.setTarget(target);
//        neoTargetRepository.save(drugTarget);

//        drug.addTarget(target);
//        System.out.println(drug.getTargets().size());
//        NeoCompound sDrug = neoCompoundRepository.save(drug);
//        System.out.println(sDrug.getTargets().size());

//        target.addDrug(drug);
//        neoGeneRepository.save(target);


//        System.out.println(movieRepository.findByTitleLike("The Matrix").size());
//        movieRepository.findByTitleLike("The Matrix").stream().forEach(new Consumer<Movie>() {
//            @Override
//            public void accept(Movie movie) {
//                System.out.println(movie.getRoles().size());
//            }
//        });
//        System.out.println(personRepository.findById(20L).get().getMovies().size());

//        dwpcQueryService.metapath_from_abbrev("CbGpPWpGaD");

//        String json = FileUtils.readFileToString(new ClassPathResource("metagraph.json").getFile());
//        JSONObject obj = new JSONObject(json);
//        JSONArray jsonArray = obj.getJSONArray("data");
//        String py = "";
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONArray abbrevArray = (JSONArray) jsonArray.get(i);
//            String abb = (String) abbrevArray.get(0);
//            String comp = (String) abbrevArray.get(5);
//            Double imresh = (Double) abbrevArray.get(2);
//            if (!comp.contains("Pathway") && !comp.contains("Cellular Component") && !comp.contains("Pharmacologic Class") && imresh > 0.05) {
//                System.out.println(abb + ":" + comp);
//                py += "'" + abb + "',";
//
//            }
//        }
//        System.out.println(py);

//        System.out.println(dwpcQueryService.createDWPCQuery("CcSEcCdGuD","DB00014","DOID:1324","0.5"));

//        Session session = sessionFactory.openSession();
//        Result result = session.query(dwpcQueryService.createDWPCQuery("CcSEcCdGuD", "DB00014", "DOID:1324", "0.5"), Collections.emptyMap());
//        result.queryResults().forEach(new Consumer<Map<String, Object>>() {
//            @Override
//            public void accept(Map<String, Object> stringObjectMap) {
//                System.out.println(stringObjectMap.keySet());
//                System.out.println(stringObjectMap.get("PC"));
//                System.out.println(stringObjectMap.get("DWPC"));
//                System.out.println("******************");
//            }
//        });

//        System.out.println(neoCompoundRepository.findById(641L).get().getIdentifier());
//        System.out.println(neoCompoundRepository.findByIdentifier("DB01078").getId());

//        String query = dwpcQueryService.createDWPCQuery("CcSEcCdGuD", "DB01148", "0.4");
//        Session session = sessionFactory.openSession();
//        Result result = session.query(query, Collections.emptyMap());
//        result.queryResults().forEach(stringObjectMap -> {
//            System.out.println(stringObjectMap.keySet());
//            System.out.println(stringObjectMap.get("DWPC"));
//            System.out.println(stringObjectMap.get("n4"));
//            NeoDisease disease = (NeoDisease) stringObjectMap.get("n4");
//            System.out.println(disease.getIdentifier());
//            System.out.println("******************");
//        });

//        System.out.println(neoCompoundRepository.getDiseaseRelByCompound("DB00515").size());

//        List<NeoCompound> allCompounds = (List<NeoCompound>) neoCompoundRepository.findAll();
//        System.out.println("allCompounds size " + allCompounds.size());

//        System.out.println("Begin Analyze #####");
//        List<String> abbrevs = Arrays.asList("CbGaD", "CrCtD", "CbGbCtD", "CrCbGaD", "CtDtCtD", "CcSEcCtD", "CbGdCrCtD", "CtDtCtDrD", "CbGuCbGaD", "CbGbCrCtD");
//        List<String> abbrevs = Arrays.asList("CbGbCrCtD");
//        long startTime = System.nanoTime();
//        abbrevs.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String abbrev) {
////                System.out.println("**** Begin Abbrev " + abbrev);
//                final Double[] allAverage = {0D};
////                final Double[] allIQR = {0D};
////                final Integer[] allICount = {0};
//                allCompounds.parallelStream().forEach(new Consumer<NeoCompound>() {
//                    @Override
//                    public void accept(NeoCompound neoCompound) {
//                        try {
//                            List<Double> doubles = new ArrayList<>();
//                            List<NeoDisease> neoDiseases = new ArrayList<>();
//                            Integer pathLen = dwpcQueryService.metapath_from_abbrev(abbrev).size();
//                            String query = dwpcQueryService.createDWPCQuery(abbrev, neoCompound.getIdentifier(), "0.5");
//                            Session session = sessionFactory.openSession();
//                            Result result = session.query(query, Collections.emptyMap());
//                            result.queryResults().forEach(stringObjectMap -> {
//                                if (stringObjectMap.get("DWPC") != null) {
//                                    NeoDisease disease = (NeoDisease) stringObjectMap.get("n" + pathLen);
//                                    disease.setDwpc((Double) stringObjectMap.get("DWPC"));
//                                    doubles.add((Double) stringObjectMap.get("DWPC"));
//                                    neoDiseases.add(disease);
//                                }
//                            });
//
//                            Collections.sort(doubles);
//                            Collections.reverse(doubles);
////                            Double[] ds = doubles.toArray(new Double[doubles.size()]);
////                            double[] data = ArrayUtils.toPrimitive(ds);
////                            DescriptiveStatistics da = new DescriptiveStatistics(data);
////                            double iqr = da.getPercentile(75) - da.getPercentile(25);
//
////                            for (int i = 0; i < data.length; i++) {
////                                if (data[i] > iqr) {
////                                    allICount[0]++;
////                                }
////                            }
//
////                            System.out.println("Var " + iqr + " Main Count " + data.length + " Char Count " + count + " Average " + average(doubles));
//
//                            List<Double> subdo = doubles.subList(0, doubles.size() / 8);
//                            if (subdo.size() > 0){
//                                Double avg = average(doubles);
////                                System.out.println(avg);
//                                allAverage[0] += avg;
//                            }
//
////                            if (!Double.isNaN(iqr))
////                                allIQR[0] += iqr;
//
////                            System.out.println("Compound "+ neoCompound.getIdentifier() +"  Number of predict Disease " + neoDiseases.size() + "  Number of Direct Disease " + neoCompound.getDiseases().size());
////                            System.out.println(neoCompound.getIdentifier() + ":" + neoDiseases.size() + ":" + neoCompound.getDiseases().size() + ":" + neoCompound.getDiseasesPalliate().size());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });

//                long endTime = System.nanoTime();
//                long duration = (endTime - startTime);
//                System.out.println("All Average " + abbrev + " : " + allAverage[0]);
//                System.out.println("All IQR : " + (allIQR[0] / allCompounds.size()));
//                System.out.println("All Count : " + allICount[0]);


//                System.out.println("abb  " + abbrev + (duration / 1000000));
//                allCompounds.forEach(new Consumer<NeoCompound>() {
//                    @Override
//                    public void accept(NeoCompound neoCompound) {
//
//                    }
//                });
//                System.out.println("**** Finish Abbrev " + abbrev);
//            }
//        });


//        System.out.println(neoCompoundRepository.getDiseaseRelByCompound("DB00515").size());


    }

    public double sum(List<Double> list) {

        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i);
        }

        return sum;
    }
}
