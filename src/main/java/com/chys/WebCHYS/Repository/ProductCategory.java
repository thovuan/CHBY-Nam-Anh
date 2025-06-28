package com.chys.WebCHYS.Repository;

import com.chys.WebCHYS.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategory extends JpaRepository<Product, String> {
}
