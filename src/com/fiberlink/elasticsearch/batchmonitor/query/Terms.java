
package com.fiberlink.elasticsearch.batchmonitor.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "_type",
    "missing",
    "total",
    "other",
    "terms"
})
public class Terms {

    @JsonProperty("_type")
    private String _type;
    @JsonProperty("missing")
    private int missing;
    @JsonProperty("total")
    private int total;
    @JsonProperty("other")
    private int other;
    @JsonProperty("terms")
    private List<Term> terms = new ArrayList<Term>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_type")
    public String get_type() {
        return _type;
    }

    @JsonProperty("_type")
    public void set_type(String _type) {
        this._type = _type;
    }

    @JsonProperty("missing")
    public int getMissing() {
        return missing;
    }

    @JsonProperty("missing")
    public void setMissing(int missing) {
        this.missing = missing;
    }

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("other")
    public int getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(int other) {
        this.other = other;
    }

    @JsonProperty("terms")
    public List<Term> getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(List<Term> terms) {
        this.terms = terms;
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
