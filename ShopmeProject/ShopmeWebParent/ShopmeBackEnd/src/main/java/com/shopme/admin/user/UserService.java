package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll(){
        return (List<User>) userRepo.findAll();
    }

    public List<Role> listRole(){
        return (List<Role>) roleRepo.findAll();
    }

    public void save(User user) {
        boolean isUpdateUser=(user.getId()!=null);

        if(isUpdateUser) {
            User existingUser = userRepo.findById(user.getId()).get();
            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
        }
        else {
            encodePassword(user);
        }
        userRepo.save(user); //function nếu tìm thấy user trong database -> thay đổi giá trị. Nếu không tìm thấy thì thêm 1 user và id tăng thêm 1 đơn v
    }
    private void encodePassword(User user){
        String encodedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepo.getUserByEmail(email);
        if(userByEmail==null) return true;
        else {
            if(id!=null){ //case : edit user (users/edit/{id})
                if(userByEmail.getId()==id) return true;
            }
        }
        return false;
    }

    public User get(Integer id) throws UserNotFoundExeption {
        try {
            return userRepo.findById(id).get();
        } catch (NoSuchElementException ex){
           throw new UserNotFoundExeption("Could not find any user with ID " +id );
        }
    }

    public boolean checkExistUserById(Integer id){
        return userRepo.existsById(id);
    }
}
