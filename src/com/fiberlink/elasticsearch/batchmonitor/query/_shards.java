
package com.fiberlink.elasticsearch.batchmonitor.query;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "total",
    "successful",
    "failed"
})
public class _shards {

    @JsonProperty("total")
    private int total;
    @JsonProperty("successful")
    private int successful;
    @JsonProperty("failed")
    private int failed;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("successful")
    public int getSuccessful() {
        return successful;
    }

    @JsonProperty("successful")
    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    @JsonProperty("failed")
    public int getFailed() {
        return failed;
    }

    @JsonProperty("failed")
    public void setFailed(int failed) {
        this.failed = failed;
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
