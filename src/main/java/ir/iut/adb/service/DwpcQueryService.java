package ir.iut.adb.service;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by rasool on 12/23/2018.
 * Email: Rasoul.Samani@gmail.com
 */

@Service
public class DwpcQueryService {

    public List<NodeTuple> metaPathFromAbbrev(String abbrev) throws IOException {
        Map<String, List<NodeTuple>> nodeTuples = new HashMap<>();

        String json = FileUtils.readFileToString(new ClassPathResource("metagraph.json").getFile());
        JSONObject obj = new JSONObject(json);
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            List<NodeTuple> tuples = new ArrayList<>();
            JSONArray array = obj.getJSONArray(key);
            for (int i = 0; i < array.length(); i++) {
                JSONArray abbrevArray = (JSONArray) array.get(i);
//                System.out.println(abbrevArray.get(0));
                tuples.add(new NodeTuple((String) abbrevArray.get(0), (String) abbrevArray.get(1), (String) abbrevArray.get(2), (String) abbrevArray.get(3)));
            }

            nodeTuples.put(key, tuples);
        }

        return nodeTuples.get(abbrev);
    }

    public String createDWPCQueryST(String abbrev, String source, String target, String dumping) throws IOException {
        List<NodeTuple> metarels = metaPathFromAbbrev(abbrev);
        String metapath_query = createCypherPath(metarels);
        StringBuilder cypherDegree = new StringBuilder();

        for (int i = 0; i < metarels.size(); i++) {
            NodeTuple tuple = metarels.get(i);
            String i0 = i + "";
            String i1 = (i + 1) + "";
            String rel_type = tuple.getKind();
            String dir0 = (tuple.getDirection().equals("backward")) ? "<-" : "-";
            String dir1 = (tuple.getDirection().equals("forward")) ? "->" : "-";
            if (i + 1 == metarels.size()) {
                cypherDegree.append(String.format("size((n%s)%s[:%s]%s()),\nsize(()%s[:%s]%s(n%s))\n", i0, dir0, rel_type, dir1, dir0, rel_type, dir1, i1));
            } else {
                cypherDegree.append(String.format("size((n%s)%s[:%s]%s()),\nsize(()%s[:%s]%s(n%s)),\n", i0, dir0, rel_type, dir1, dir0, rel_type, dir1, i1));
            }
        }

        String using_query = String.format("\nUSING JOIN ON n%s", metarels.size() / 2);
        String unique_nodes_query = "";
        String query = "MATCH path = %s%s\n" +
                "WHERE n0.identifier = '%s'\n" +
                "AND n%s.identifier = '%s'%s\n" +
                "WITH\n" +
                "[\n" +
                "%s\n" +
                "] AS degrees, path\n" +
                "RETURN\n" +
                "count(path) AS PC,\n" +
                "sum(reduce(pdp = 1.0, d in degrees| pdp * d ^ -%s)) AS DWPC";
        query = String.format(query, metapath_query, using_query, source, String.valueOf(metarels.size()), target, unique_nodes_query, cypherDegree.toString(), dumping);

        return query;
    }

    public String createDWPCQuery(String abbrev, String source, String dumping) throws IOException {
        List<NodeTuple> metarels = metaPathFromAbbrev(abbrev);
        String metapath_query = createCypherPath(metarels);

        StringBuilder cypherDegree = new StringBuilder();
        for (int i = 0; i < metarels.size(); i++) {
            NodeTuple tuple = metarels.get(i);
            String i0 = i + "";
            String i1 = (i + 1) + "";
            String rel_type = tuple.getKind();
            String dir0 = (tuple.getDirection().equals("backward")) ? "<-" : "-";
            String dir1 = (tuple.getDirection().equals("forward")) ? "->" : "-";
            if (i + 1 == metarels.size()) {
                cypherDegree.append(String.format("size((n%s)%s[:%s]%s()),\nsize(()%s[:%s]%s(n%s))\n", i0, dir0, rel_type, dir1, dir0, rel_type, dir1, i1));
            } else {
                cypherDegree.append(String.format("size((n%s)%s[:%s]%s()),\nsize(()%s[:%s]%s(n%s)),\n", i0, dir0, rel_type, dir1, dir0, rel_type, dir1, i1));
            }
        }

        String using_query = String.format("\nUSING JOIN ON n%s", metarels.size() / 2);
        String unique_nodes_query = "";
        String query = "MATCH path = %s%s\n" +
                "WHERE n0.identifier = '%s'\n" +
                "%s\n" +
                "WITH\n" +
                "[\n" +
                "%s\n" +
                "] AS degrees, path, n%s \n" +
                "RETURN\n" +
                "n%s ,\n" +
                "sum(reduce(pdp = 1.0, d in degrees| pdp * d ^ -%s)) AS DWPC ORDER BY DWPC DESC";
        query = String.format(query, metapath_query, using_query, source, unique_nodes_query, cypherDegree.toString(), String.valueOf(metarels.size()), String.valueOf(metarels.size()), dumping);
        return query;
    }

    private String createCypherPath(List<NodeTuple> metarels) {
        String q = String.format("(n0:%s)", metarels.get(0).getSource());
        for (int i = 0; i < metarels.size(); i++) {
            NodeTuple tuple = metarels.get(i);
            String index = (i + 1) + "";
            String rel_type = tuple.getKind();
            String target_label = (i + 1 == metarels.size()) ? String.format(":%s", tuple.getTarget()) : "";
            String dir0 = (tuple.getDirection().equals("backward")) ? "<-" : "-";
            String dir1 = (tuple.getDirection().equals("forward")) ? "->" : "-";
            q += String.format("%s[:%s]%s(n%s%s)", dir0, rel_type, dir1, index, target_label);
        }
        return q;
    }
}
