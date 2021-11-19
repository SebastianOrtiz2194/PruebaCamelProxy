
package prueba.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect
@JsonSerialize
@JsonPropertyOrder({
	"ID",
    "NAME",
    "AGE"
})
@Generated("jsonschema2pojo")
public class User implements Serializable {

    @JsonProperty("ID")
    private Integer id;
	@JsonProperty("NAME")
    private String name;
    @JsonProperty("AGE")
    private Integer age;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NAME")
    public String getName() {
        return name;
    }

    @JsonProperty("NAME")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("AGE")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("AGE")
    public void setAge(Integer age) {
        this.age = age;
    }
    
    @JsonProperty("ID")
    public Integer getId() {
		return id;
	}
    
    @JsonProperty("ID")
	public void setId(Integer id) {
		this.id = id;
	}

    public User() {
		super();
		// TODO Auto-generated constructor stub
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
