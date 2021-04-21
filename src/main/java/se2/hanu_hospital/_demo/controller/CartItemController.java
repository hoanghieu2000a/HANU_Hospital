package sqa.hanu_minimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.payload.CartItemPayLoad;
import sqa.hanu_minimart.service.CartItemService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/cartItem"})
public class CartItemController {
	@Autowired
	private final CartItemService cartItemService;

	public CartItemController(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		try{
			return new ResponseEntity<>(cartItemService.getAllItem(), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		try{
			return new ResponseEntity<>(cartItemService.getById(id), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> addNewItem(@RequestBody CartItemPayLoad newItem) {
		try{
			return new ResponseEntity<>(cartItemService.addNewItem(newItem), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCartItem(@PathVariable Long id, @RequestParam int quantity) {
		try{
			cartItemService.updateItem(id, quantity);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteAllCartItem() {
		try{
			cartItemService.deleteAll();
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCartItem(@PathVariable Long id) {
		try{
			cartItemService.deleteItem(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}