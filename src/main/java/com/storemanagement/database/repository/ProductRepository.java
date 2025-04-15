package com.storemanagement.database.repository;

import com.storemanagement.database.dto.ProductCloudDTO;
import com.storemanagement.database.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.isSelected = :selected")
    List<Product> findAllBySelectedIs(@Param("selected") boolean selected);


    @Query("SELECT p FROM Product p WHERE p.isSynced = false")
    List<ProductCloudDTO> findAllNotSynced();

    List<Product> findAllByNameIn(List<String> names);
}
