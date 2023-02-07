package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erudio.model.Person;
//Utilizar o @Service ou @Repository é para identificar mais facil que tipo de classe estamos lidando,a funcionalidade é a mesma para ambos
public interface PersonRepository extends JpaRepository<Person, Long>{}
