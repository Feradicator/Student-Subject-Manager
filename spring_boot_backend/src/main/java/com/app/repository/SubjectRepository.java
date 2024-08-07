package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.app.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
