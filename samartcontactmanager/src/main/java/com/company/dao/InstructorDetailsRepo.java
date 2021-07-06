package com.company.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.entities.InstructorDetails;

public interface InstructorDetailsRepo extends JpaRepository<InstructorDetails,Integer>
{

}