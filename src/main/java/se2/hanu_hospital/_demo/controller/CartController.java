package sqa.hanu_minimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.service.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/cart"})
public class CartController {
	@Autowired
	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		try{
			return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getByUser")
	public ResponseEntity<?> getByUser(@RequestParam Long userId){
		try{
			return new ResponseEntity<>(cartService.getByUserId(userId), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try{
			return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> newCart(@RequestBody Cart newCart) {
		try{
			return new ResponseEntity<>(cartService.addNewCart(newCart), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCart(@PathVariable Long id, @RequestBody Cart cart){
		try{
			cartService.updateCart(id, cart);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteCart(@PathVariable Long id) {
		cartService.deleteCart(id);
	}
}
