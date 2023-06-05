package de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.tudresden.inf.st.mathgrass.api.graph.Edge;
import de.tudresden.inf.st.mathgrass.api.graph.Graph;
import de.tudresden.inf.st.mathgrass.api.graph.GraphRepository;
import de.tudresden.inf.st.mathgrass.api.graph.Vertex;
import de.tudresden.inf.st.mathgrass.api.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@SpringBootApplication
class AdminControllerTest {


public static void main(String[] args){




        String json = "{\"cells\":[{\"type\":\"standard.Rectangle\",\"position\":{\"x\":240,\"y\":30},\"size\":{\"width\":100,\"height\":40},\"angle\":0,\"id\":\"00060b88-4f90-4057-ab24-b64b41dfa731\",\"z\":1,\"attrs\":{\"body\":{\"stroke\":\"black\"},\"label\":{\"text\":\"rect\"}}},{\"type\":\"standard.Circle\",\"position\":{\"x\":390,\"y\":120},\"size\":{\"width\":100,\"height\":40},\"angle\":0,\"id\":\"0e49beb7-53fc-4436-bf04-a7118b6f257d\",\"z\":2,\"attrs\":{\"body\":{\"stroke\":\"blue\"},\"label\":{\"text\":\"cir\"}}},{\"type\":\"standard.Rectangle\",\"position\":{\"x\":150,\"y\":210},\"size\":{\"width\":100,\"height\":40},\"angle\":0,\"id\":\"204181a9-f27f-4e17-8492-800b730f4018\",\"z\":3,\"attrs\":{\"body\":{\"stroke\":\"blue\"},\"label\":{\"text\":\"SRK\"}}},{\"type\":\"standard.Link\",\"source\":{\"id\":\"00060b88-4f90-4057-ab24-b64b41dfa731\"},\"target\":{\"id\":\"204181a9-f27f-4e17-8492-800b730f4018\"},\"id\":\"5c52ee24-d0e9-4ca0-a6e6-1d99791eaf28\",\"labels\":[{\"attrs\":{\"text\":{\"text\":\"Directed\"}}}],\"z\":4,\"attrs\":{\"line\":{\"stroke\":\"black\",\"targetMarker\":{\"size\":5,\"attrs\":{\"fill\":\"black\"}},\"width\":1}}},{\"type\":\"standard.Link\",\"source\":{\"id\":\"204181a9-f27f-4e17-8492-800b730f4018\"},\"target\":{\"id\":\"0e49beb7-53fc-4436-bf04-a7118b6f257d\"},\"id\":\"0fb4a368-b2ec-4090-84d2-997f505a9b06\",\"labels\":[{\"attrs\":{\"text\":{\"text\":\"Directed\"}}}],\"z\":5,\"attrs\":{\"line\":{\"stroke\":\"black\",\"targetMarker\":{\"size\":5,\"attrs\":{\"fill\":\"black\"}},\"width\":1}}},{\"type\":\"standard.Link\",\"source\":{\"id\":\"0e49beb7-53fc-4436-bf04-a7118b6f257d\"},\"target\":{\"id\":\"00060b88-4f90-4057-ab24-b64b41dfa731\"},\"id\":\"b87c4c0a-e286-4441-a8ac-c5c7855c943e\",\"labels\":[{\"attrs\":{\"text\":{\"text\":\"Directed\"}}}],\"z\":6,\"attrs\":{\"line\":{\"stroke\":\"black\",\"targetMarker\":{\"size\":5,\"attrs\":{\"fill\":\"black\"}},\"width\":1}}}]}";
        try{
            JsonParser parser = new JsonParser();
            //Creating JSONObject from String using parser
            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
            JsonArray cellsArray = jsonObject.getAsJsonArray("cells");
//            for (JsonElement element : cellsArray) {
//                JsonObject cellObject = element.getAsJsonObject();
//                // Perform operations on each cellObject
//                // Example: Access specific values using cellObject.get("key")
//                System.out.println("type: "+cellObject.get("type"));
//                System.out.println("position: "+cellObject.get("position"));
//                JsonObject positionObject = cellObject.getAsJsonObject("position");
//                int x = positionObject.get("x").getAsInt();
//                int y = positionObject.get("y").getAsInt();
//                System.out.println("x: " + x);
//                System.out.println("y: " + y);
//                System.out.println("size: "+cellObject.get("size"));
//                System.out.println("id: "+cellObject.get("id"));
//                JsonObject attrsObject = cellObject.getAsJsonObject("attrs");
//                System.out.println("attrs: " + attrsObject);
//                JsonObject bodyObject = attrsObject.getAsJsonObject("body");
//                JsonObject labelObject = attrsObject.getAsJsonObject("label");
//                String stroke = String.valueOf(bodyObject.get("stroke"));
//                String label = String.valueOf(labelObject.get("text"));
//                System.out.println("stroke: " + stroke);
//                System.out.println("label: " + label);
//
//
//                // ...
//            }

            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();

// Iterate over the cells array
            for (JsonElement element : cellsArray) {
                JsonObject cellObject = element.getAsJsonObject();

                // Check the type of the cell
                String cellType = cellObject.get("type").getAsString();

                if (cellType.equals("standard.Rectangle") || cellType.equals("standard.Circle")) {
                    // Create a new Vertex object
                    Vertex vertex = new Vertex();
                    vertex.setLabel(cellObject.get("id").getAsString());

                    JsonObject positionObject = cellObject.getAsJsonObject("position");
                    int x = positionObject.get("x").getAsInt();
                    int y = positionObject.get("y").getAsInt();
                    vertex.setX(x);
                    vertex.setY(y);

                    vertices.add(vertex);
                } else if (cellType.equals("standard.Link")) {
                    // Create a new Edge object
                    Edge edge = new Edge();

                    JsonObject sourceObject = cellObject.getAsJsonObject("source");
                    String sourceId = sourceObject.get("id").getAsString();
                    Vertex sourceVertex = findVertexById(vertices, sourceId);
                    edge.setSourceVertex(sourceVertex);

                    JsonObject targetObject = cellObject.getAsJsonObject("target");
                    String targetId = targetObject.get("id").getAsString();
                    Vertex targetVertex = findVertexById(vertices, targetId);
                    edge.setTargetVertex(targetVertex);

                    System.out.println(edge);

                    edges.add(edge);
                }
            }
            // Create a Graph object and set the vertices and edges
            Graph graph = new Graph();
            graph.setVertices(vertices);
            graph.setEdges(edges);
            System.out.println(graph.getVertices().toString());
//// Save the graph using the appropriate repository method
//            graphRepository.save(graph);
//            System.out.println("Vertices came "+ vertices);
//            System.out.println("edges came "+edges.size());

//            System.out.println("After Parsing "+JSONObject.get("cells"));
        }
        catch (Exception e){
            System.out.println("Json parsing exception {}"+e.getMessage());
        }
    }

    private static Vertex findVertexById(List<Vertex> vertices, String sourceId) {
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(sourceId)) {
                return vertex;
            }
        }
        return null;
    }
}


