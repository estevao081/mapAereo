package dev.estv.mapAereo.repositories;

import dev.estv.mapAereo.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID>{

}