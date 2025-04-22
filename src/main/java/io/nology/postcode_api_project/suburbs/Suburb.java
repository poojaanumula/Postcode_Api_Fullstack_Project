package io.nology.postcode_api_project.suburbs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.nology.postcode_api_project.postcodes.Postcode;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "suburbs")
public class Suburb {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(nullable = false, unique = true)
private String name;

@ManyToOne
@JoinColumn(name = "postcode_id", nullable = false)
@JsonIgnoreProperties("suburbs")
private Postcode postcode;

public String getName() {
    return name;
}

public Postcode getPostcode() {
    return postcode;
}

public void setPostcode(Postcode postcode) {
    this.postcode = postcode;
}

public void setName(String name) {
    this.name = name;
}

}
