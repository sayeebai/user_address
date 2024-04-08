package com.sayu.user_address_crud.repository;

import com.sayu.user_address_crud.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
   @Query(value = "select p from AddressEntity p where p.city= :city")
    List<AddressEntity> findAllAddressEntitiesByCity(@Param("city") String city);
}
