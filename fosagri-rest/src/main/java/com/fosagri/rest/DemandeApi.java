package com.fosagri.rest;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/relais")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DemandeApi {

}