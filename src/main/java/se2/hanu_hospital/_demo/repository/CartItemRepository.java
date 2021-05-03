package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sqa.hanu_minimart.model.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    public void deleteAllByCart_Id(Long cartId);
}
