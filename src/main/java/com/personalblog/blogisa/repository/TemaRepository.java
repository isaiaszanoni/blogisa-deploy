package com.personalblog.blogisa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalblog.blogisa.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

	List<Tema> findAllByNomeTemaContainingIgnoreCase(String nomeTema);

}
