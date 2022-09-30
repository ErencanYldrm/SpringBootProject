package kodlama.northwind.api.controllers;


import kodlama.northwind.business.abstracts.UserService;
import kodlama.northwind.core.entities.User;
import kodlama.northwind.core.utilities.results.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

        private UserService userService;

        @Autowired
    public UsersController(UserService userService) {
            super();
            this.userService = userService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.add(user));
    }
    //@valid istediğimiz gereksinimleri karşılıyor mu kontrol ediyor karşılıyorsa çalıştırıyor
    //@requestbody post işlemlerinde veri dönüşümünü sağlıyor
    //@requestparam get işlemlerinde veri dönüşümünü sağlıyor

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String,String>();
        for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorResult<Object> errors = new ErrorResult<Object>(validationErrors,"Doğrulama hataları");
        return errors;
    }
}
