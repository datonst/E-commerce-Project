package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository roleRepo;
    @Test
    public void testCreateFirst(){
        Role roleAdmin=new Role("Admin","manage everything");
        Role saveRole= roleRepo.save(roleAdmin);
        assertThat(saveRole.getId()).isGreaterThan(0);
    }
    @Test
    public void testCreateSecond(){
        Role roleSalesperson=new Role("Salesperson","manage product price "+"customers, shipping, orders and sales report");
        Role roleSipper=new Role("Shipper","viewproducts,view orders "+"and update order status");
        Role roleEditor=new Role("Editor","manage categories,brands "+"products, articles and menus");
        Role roleAssistant= new Role("Assistant","manage questions and reviews");
        roleRepo.saveAll(List.of(roleSalesperson,roleSipper,roleEditor,roleAssistant));
    }

    @Test
    public void testDeleteUser(){
        roleRepo.deleteAll();
    }

}
