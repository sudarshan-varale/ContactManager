package com.company.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.company.entities.Instructor;

public interface InstructorRepo extends JpaRepository<Instructor,Integer>
{

}