package sqa.hanu_minimart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.payload.CartItemPayLoad;
import sqa.hanu_minimart.repository.CartItemRepository;
import sqa.hanu_minimart.repository.CartRepository;

import javax.transaction.Transactional;

@Service
public class CartItemService {

	@Autowired
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final ProductService productService;

	public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductService productService) {
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.productService = productService;
	}

	public List<CartItem> getAllItem(){
		return cartItemRepository.findAll();
	}

	public CartItem getById(Long id) {
		return cartItemRepository.findById(id).get();
	}

	public CartItem addNewItem(CartItemPayLoad cartItemPayLoad) {
		Cart cart = cartRepository.findCartById(cartItemPayLoad.getCartId());
		Product product = productService.findProductByNameSortedByExpAndImportDate(cartItemPayLoad.getProductName()).get(0);
		Double price = product.getPrice() * cartItemPayLoad.getQuantity();

		CartItem cartItem = new CartItem(cart, cartItemPayLoad.getProductName(), cartItemPayLoad.getQuantity(), cartItemPayLoad.getContent());
		cartItem.setPrice(product.getPrice());
		cart.getCartItem().add(cartItem);
                if(cart.getTotalPrice() == null)
                    cart.setTotalPrice(0.0);
		cart.setTotalPrice(cart.getTotalPrice() + price);

		return cartItemRepository.save(cartItem);
	}

	@Transactional
	public void deleteItem(Long id) {
		if(!cartItemRepository.existsById(id)) {
			throw new IllegalStateException("Item is not exits");
		}
		CartItem cartItem = cartItemRepository.findById(id).get();
		Cart cart = cartItem.getCart();

		if (cart.getTotalPrice() != null){
			cart.setTotalPrice(cart.getTotalPrice() - cartItem.getPrice() * cartItem.getQuantity());
		}
		cartRepository.save(cart);

		cartItemRepository.deleteById(id);
	}
	public void deleteAll() {
		cartItemRepository.deleteAll();
	}

	@Transactional
	public void deleteByCartId(Long cartId) {
		cartItemRepository.deleteAllByCart_Id(cartId);
	}

	@Transactional
	public void updateItem(Long id, int quantity) {
		CartItem cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Item does not exist!"));
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
	}
}
