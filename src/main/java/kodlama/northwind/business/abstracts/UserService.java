package kodlama.northwind.business.abstracts;

import kodlama.northwind.core.entities.User;
import kodlama.northwind.core.utilities.results.DataResult;
import kodlama.northwind.core.utilities.results.Result;

public interface UserService {

    Result add(User user);
    DataResult<User> findByEmail(String email);
}
