
package com.fiberlink.elasticsearch.batchmonitor.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "total",
    "max_score",
    "hits"
})
public class Hits {

    @JsonProperty("total")
    private int total;
    @JsonProperty("max_score")
    private double max_score;
    @JsonProperty("hits")
    private List<Object> hits = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("max_score")
    public double getMax_score() {
        return max_score;
    }

    @JsonProperty("max_score")
    public void setMax_score(double max_score) {
        this.max_score = max_score;
    }

    @JsonProperty("hits")
    public List<Object> getHits() {
        return hits;
    }

    @JsonProperty("hits")
    public void setHits(List<Object> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
