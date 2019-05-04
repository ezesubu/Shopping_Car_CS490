package edu.mum.cs490.shoppingcart.service.impl;

import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.domain.User;
import edu.mum.cs490.shoppingcart.repository.AddressRepository;
import edu.mum.cs490.shoppingcart.repository.CardDetailRepository;
import edu.mum.cs490.shoppingcart.repository.UserRepository;
import edu.mum.cs490.shoppingcart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Primary
@Service
public class UserServiceImpl<T extends User> implements IUserService<T> {

    private final UserRepository<T> userRepository;

    @Autowired
    private CardDetailRepository cardDetailRepository;

    @Autowired
    private AddressRepository addressRepository;

    public UserServiceImpl(UserRepository<T> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public T getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public T getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Boolean existByIdNotAndUsername(Integer id, String username) {
        return userRepository.existByIdNotAndUsername(id, username);
    }

    @Override
    public List<T> getAll() {
        return userRepository.findAll();
    }

    @Override
    public T saveOrUpdate(T user) {
        return userRepository.save(user);
    }

    @Override
    public T loadUserByUsername(String username) throws UsernameNotFoundException {
        T user = userRepository.getByUsername(username);
        if (user != null)
            return user;
        else
            throw new UsernameNotFoundException("Username not found");
    }

    @Override
    public void delete(Integer id) {
        changeStatus(id, Status.DELETED);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        T user = getById(id);
        user.setStatus(status);
        userRepository.save(user);
    }

    @Override
    public void disableCard(Integer cardId) {
        cardDetailRepository.disableCard(cardId);
    }

    @Override
    public void disableAddress(Integer addressId) {
        this.addressRepository.disableAddress(addressId);
    }
}
