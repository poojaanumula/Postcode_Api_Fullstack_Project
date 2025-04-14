package io.nology.postcode_api_project.postcodes;

import java.util.List;

import io.nology.postcode_api_project.suburbs.Suburb;
import jakarta.persistence.*;

@Entity
@Table(name = "postcodes")
public class Postcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "postcode", cascade = CascadeType.ALL)
    
    private List<Suburb> suburbs;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Suburb> getSuburbs() {
        return suburbs;
    }

    public void setSuburbs(List<Suburb> suburbs) {
        this.suburbs = suburbs;
    }

    public Postcode() {
    }

    public Postcode(String code, List<Suburb> suburbs) {
        this.code = code;
        this.suburbs = suburbs;
    }

   
}
