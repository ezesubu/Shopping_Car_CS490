package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Transactional(readOnly = true)
public interface UserService<T extends User> extends UserDetailsService{

    T getById(Integer id);

    T getByUsername(String username);

    Boolean existByIdNotAndUsername(Integer id, String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<T> getAll();

    @Transactional
    @PreAuthorize("#user.id == NULL or #user.id == principal.id or hasRole('ROLE_ADMIN')")
    T  saveOrUpdate(T user);

    @Transactional
    @PreAuthorize("#user.id == NULL or #user.id == principal.id or hasRole('ROLE_ADMIN')")
    void delete(Integer id);

    @Transactional
    @PreAuthorize("#user.id == NULL or #user.id == principal.id or hasRole('ROLE_ADMIN')")
    void changeStatus(Integer id, Status status);

    @Transactional
    void disableCard(Integer cardId);

    @Transactional
    public void disableAddress(Integer addressId);
}
