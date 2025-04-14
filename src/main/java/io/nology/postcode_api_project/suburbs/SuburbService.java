package io.nology.postcode_api_project.suburbs;

import java.util.List;

import org.springframework.stereotype.Service;
import io.nology.postcode_api_project.common.exceptions.ConflictExceptions;
import io.nology.postcode_api_project.postcodes.Postcode;
import io.nology.postcode_api_project.postcodes.PostcodeRepository;

@Service
public class SuburbService {

    private final SuburbRepository suburbRepo;
    private final PostcodeRepository postcodeRepo;

    public SuburbService(SuburbRepository suburbRepo,
                         PostcodeRepository postcodeRepo) {
        this.suburbRepo = suburbRepo;
        this.postcodeRepo = postcodeRepo;
    }

    public Suburb createSuburb(CreateSuburbDTO data) throws ConflictExceptions {
        String suburbName = data.getName().trim();
        String code  = data.getPostcode().trim();

        // 1. Find or create Postcode
        Postcode postcode = postcodeRepo.findByCode(code)
            .orElseGet(() -> {
                Postcode p = new Postcode();
                p.setCode(code);
                return postcodeRepo.save(p);
            });

        // 2. Prevent duplicate suburb+postcode combos
        if (suburbRepo.existsByName(suburbName)) {
            throw new ConflictExceptions("Suburb name must be unique");
        }
        // 3. Create and link new suburb
        Suburb suburb = new Suburb();
        suburb.setName(suburbName);
        suburb.setPostcode(postcode);

        // 4. Persist and return
        return suburbRepo.save(suburb);
    }

    public List<Suburb> getAllSuburbs() {
    
            return this.suburbRepo.findAll();
        
    
    }
}
