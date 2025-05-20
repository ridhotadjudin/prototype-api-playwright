package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FactResponse {
    private String fact;
    private int length;

    // Default constructor required for Jackson
    public FactResponse() {
    }

    // Constructor for manual creation
    public FactResponse(String fact, int length) {
        this.fact = fact;
        this.length = length;
    }

    // Getters and setters with optional Jackson annotations
    @JsonProperty("fact")
    public String getFact() {
        System.out.println("JSON Property Fact");
        return fact;
    }

    @JsonProperty("fact")
    public void setFact(String fact) {
        this.fact = fact;
    }

    @JsonProperty("length")
    public int getLength() {
        System.out.println("JSON Property Length");
        return length;
    }

    @JsonProperty("length")
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        System.out.println("Override to string");
        return "FactResponse{" +
                "fact='" + fact + '\'' +
                ", length=" + length +
                '}';
    }
}
