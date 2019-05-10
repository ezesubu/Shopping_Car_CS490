/*
 * Created by: Ezesubu
 * Date: April 20, 2019
 * Name: View
 * DependingIn:  Boostrap3.6.->
 * Description: UI Template
 * Module: UI Template
 */

package edu.mum.cs490.shoppingcart.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import java.util.Collection;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Entity
public class SuperAdmin extends User {
    private String firstName;
    private String lastName;

    public SuperAdmin(){
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_SUPER_ADMIN");
    }
}
