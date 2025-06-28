package com.chys.WebCHYS.Repository;

import com.chys.WebCHYS.Model.Entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, String> {
}
