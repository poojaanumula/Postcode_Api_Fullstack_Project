package io.nology.postcode_api_project.suburbs;

import org.springframework.data.jpa.repository.JpaRepository;



public interface SuburbRepository extends JpaRepository<Suburb, Long> {
    boolean existsByName(String name); // Only check if suburb name already exists
}