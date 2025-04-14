package io.nology.postcode_api_project.suburbs;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.postcode_api_project.common.exceptions.ConflictExceptions;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/suburbs")
public class SuburbController {

    private SuburbService suburbService;

    public SuburbController(SuburbService suburbService) {
        this.suburbService = suburbService;
    }

    @PostMapping
    public ResponseEntity<Suburb> postSuburb(@RequestBody @Valid CreateSuburbDTO data) throws ConflictExceptions
    {
        Suburb newSuburb = this.suburbService.createSuburb(data);
        return new ResponseEntity<Suburb>(newSuburb, HttpStatus.CREATED);
    }
    
@GetMapping
public ResponseEntity<List<Suburb>> getMethodName() {
    List<Suburb> suburbs = this.suburbService.getAllSuburbs();
    return new ResponseEntity<>(suburbs, HttpStatus.OK);
}




}
