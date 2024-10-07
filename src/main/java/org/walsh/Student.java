package org.walsh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private final HashMap<IGradable, Grade> _grades;
    private final StudentType _studentType;  // New field for student type

    public Student(String name, String id, StudentType studentType) {
        super(name, id);
        _grades = new HashMap<>();
        _studentType = studentType;
    }

    public StudentType getStudentType() {
        return _studentType;
    }

    public void addGrade(IGradable gradable, int grade) {
        _grades.put(gradable, new Grade(grade));
    }

    public Integer getGrade(IGradable gradable) {
        var grade = _grades.get(gradable);
        return grade != null ? grade.getPoints() : null;
    }

    public double calculateAverageGrade() {
        if (_grades.isEmpty()) return 0;
        double total = 0;
        for (Grade grade : _grades.values()) {
            total += grade.getPoints();
        }
        return total / _grades.size();
    }


    public void generateJson() {
            var mapper = new ObjectMapper();

            // Create a root JSON object
            var rootNode = mapper.createObjectNode();

            // Add student data
            rootNode.put("studentName", getName());
            rootNode.put("studentId", getId());
            rootNode.put("studentType", getStudentType().toString());

            var grades = mapper.createObjectNode();
            for (Map.Entry<IGradable, Grade> entry : _grades.entrySet()) {
                grades.put(entry.getKey().getName(), entry.getValue().getPoints());
            }
            rootNode.set("grades", grades);

        try {
            // Convert the mapper to a JSON string
            JSON_PAYLOAD = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void simulateAPIPost(){
        generateJson();
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JSON_PAYLOAD))
                .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}