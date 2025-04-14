package io.nology.postcode_api_project.postcodes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, Long>{
    Optional<Postcode> findByCode(String code);
}
