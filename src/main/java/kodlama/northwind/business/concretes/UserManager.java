package kodlama.northwind.business.concretes;

import kodlama.northwind.business.abstracts.UserService;
import kodlama.northwind.core.dataAccess.UserDao;
import kodlama.northwind.core.entities.User;
import kodlama.northwind.core.utilities.results.DataResult;
import kodlama.northwind.core.utilities.results.Result;
import kodlama.northwind.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    @Autowired
    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }


    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult<User>("Kullanıcı eklendi");
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessResult<User>(this.userDao.findByEmail(email),"Kullanıcı bulundu");
    }
}
