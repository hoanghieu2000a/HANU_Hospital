package se2.hanu_hospital.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.account.payload.UserPayload;
import se2.hanu_hospital.account.repository.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path="/getAll")
    public ResponseEntity<?> getAllAccount( @RequestParam(required = false) Long id,
                                               @RequestParam(required = false, defaultValue = "_") String name,
                                               @RequestParam(required = false) String username,
                                               @RequestParam(required = false) String phoneNumber,
                                               @RequestParam(required = false, defaultValue = "_") String address,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,

                                               @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            return new ResponseEntity<>(accountService.getAllAccount(id, username, phoneNumber, name, address, page, size, sort ), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="/update")
    public ResponseEntity<?> updateAccount (@RequestParam Long id, @Valid @RequestBody UserPayload request){
        return new ResponseEntity<>(accountService.updateUser(id, request), HttpStatus.OK);
    }

    @GetMapping(path="/delete")
    public ResponseEntity<?> deleteAccount (@RequestParam Long id){
        accountService.deleteUser(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
