package com.daicq.service.impl;

import com.daicq.repository.UserRepository;
import com.daicq.service.UsersService;
import com.daicq.service.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UsersService usersService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      //  User user = userRepository.findUserByLogin(userName);
        UsersDTO usersDTO = usersService.findByUserName(userName);
        if (usersDTO == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        System.out.println("Found User: " + usersDTO);

        // [ROLE_USER, ROLE_ADMIN,..]
      //  List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        List<String> roleNames = usersDTO.getRoles();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(usersDTO.getUserName(), //
            usersDTO.getPassword(), grantList);

        return userDetails;
    }
}
