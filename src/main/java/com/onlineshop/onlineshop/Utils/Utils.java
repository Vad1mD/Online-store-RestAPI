package com.onlineshop.onlineshop.Utils;

import com.onlineshop.onlineshop.dataModels.Users;
import com.onlineshop.onlineshop.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Utils {

    private UserRepository userRepository;

    @Autowired
    public Utils(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean validateOwner(Long ownerId){
        Optional<Users> owner = userRepository.findById(ownerId);
        return owner.isPresent() && owner.get().getRole().equalsIgnoreCase("owner");
    }

    public boolean validateUser(Long userId){
        return userRepository.existsById(userId);
    }

    public boolean validateCustomer(Long id){
        Optional<Users> user = userRepository.findById(id);
        return user.isPresent() && user.get().getRole().equalsIgnoreCase("customer");
    }
}
