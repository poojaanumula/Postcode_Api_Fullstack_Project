package io.nology.postcode_api_project.suburbs;

import jakarta.validation.constraints.NotBlank;

public class CreateSuburbDTO {
    
    @NotBlank(message = "Suburb name is required")
    private String name;

    @NotBlank(message = "Postcode is required")
    private String postcode;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "CreateSuburbDTO [name=" + name + ", postcode=" + postcode + "]";
    }
}
