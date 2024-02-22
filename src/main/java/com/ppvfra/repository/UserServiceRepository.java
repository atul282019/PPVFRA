package com.ppvfra.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;


import com.ppvfra.entity.Role;


public interface UserServiceRepository extends JpaRepository<Role, Long> 
{

}