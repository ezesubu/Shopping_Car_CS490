/*
 * Created by: Ezesubu
 * Date: April 20, 2019
 * Name: View
 * DependingIn:  Boostrap3.6.->
 * Description: UI Template
 * Module: UI Template
 */

package edu.mum.cs490.shoppingcart.model.form.user;

import edu.mum.cs490.shoppingcart.domain.Admin;
import edu.mum.cs490.shoppingcart.domain.SuperAdmin;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class SuperAdminForm extends UserForm implements Serializable {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public SuperAdminForm() {
    }

    public SuperAdminForm(SuperAdmin sadmin) {
        super(sadmin);
        this.firstName = sadmin.getFirstName();
        this.lastName = sadmin.getLastName();
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
}
