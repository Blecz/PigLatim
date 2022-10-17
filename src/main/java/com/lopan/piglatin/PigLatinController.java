package com.lopan.piglatin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PigLatinController {

    @Autowired
    private PigLatinService service;

    @PostMapping(
            value = "/translate",
            produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public String translate(@RequestBody String english) {
        return service.translate(english);
    }

}
