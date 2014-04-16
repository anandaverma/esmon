
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
    "took",
    "timed_out",
    "_shards",
    "hits",
    "facets"
})
public class FacetQuery {

    @JsonProperty("took")
    private int took;
    @JsonProperty("timed_out")
    private boolean timed_out;
    @JsonProperty("_shards")
    private com.fiberlink.elasticsearch.batchmonitor.query._shards _shards;
    @JsonProperty("hits")
    private Hits hits;
    @JsonProperty("facets")
    private Facets facets;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("took")
    public int getTook() {
        return took;
    }

    @JsonProperty("took")
    public void setTook(int took) {
        this.took = took;
    }

    @JsonProperty("timed_out")
    public boolean isTimed_out() {
        return timed_out;
    }

    @JsonProperty("timed_out")
    public void setTimed_out(boolean timed_out) {
        this.timed_out = timed_out;
    }

    @JsonProperty("_shards")
    public com.fiberlink.elasticsearch.batchmonitor.query._shards get_shards() {
        return _shards;
    }

    @JsonProperty("_shards")
    public void set_shards(com.fiberlink.elasticsearch.batchmonitor.query._shards _shards) {
        this._shards = _shards;
    }

    @JsonProperty("hits")
    public Hits getHits() {
        return hits;
    }

    @JsonProperty("hits")
    public void setHits(Hits hits) {
        this.hits = hits;
    }

    @JsonProperty("facets")
    public Facets getFacets() {
        return facets;
    }

    @JsonProperty("facets")
    public void setFacets(Facets facets) {
        this.facets = facets;
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
