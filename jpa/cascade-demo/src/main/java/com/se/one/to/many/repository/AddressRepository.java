package com.se.one.to.many.repository;


import com.se.one.to.many.entity.Address;
import com.se.one.to.one.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}