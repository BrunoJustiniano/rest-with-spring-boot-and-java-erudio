package br.com.erudio.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;

	//findById all people
		//@RequestMapping(method=RequestMethod.GET,
			//	produces = MediaType.APPLICATION_JSON_VALUE)
	
		@GetMapping(
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public List<PersonVO> findAll() {
			return service.findAll();
		}
		//findById one PersonVOV2
		@GetMapping(value = "/{id}",
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public PersonVO findByID(@PathVariable(value = "id")Long id) {
			return service.findById(id);
		}
		//findById create PersonVOV2
		@PostMapping(
				consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public PersonVO create(@RequestBody PersonVO Person) {
			
			return service.create(Person);
		}
		//findById create PersonVOV2
		@PostMapping(value = "/v2", 
				consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public PersonVOV2 createV2(@RequestBody PersonVOV2 Person) {
			
			return service.createV2(Person);
		}
		//findById update PersonVOV2
		@PutMapping(
				consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public PersonVO update(@RequestBody PersonVO Person) {
			
			return service.update(Person);
		}
		//findById delete PersonVOV2
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<?> delete(@PathVariable(value = "id")Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
	
	}
