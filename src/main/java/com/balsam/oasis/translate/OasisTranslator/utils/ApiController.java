package com.balsam.oasis.translate.OasisTranslator.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/oasis-translator")
public class ApiController {

    @Autowired
    private ApiService apiService ;

    @RequestMapping(value = "/{sourceLang}/{targetLang}/{text}")
    public ResponseEntity<String> translate(@PathVariable String sourceLang,@PathVariable String targetLang,@PathVariable String text){
        return  new ResponseEntity<>(apiService.translate(sourceLang,targetLang, text), HttpStatus.OK) ;
    }
}
