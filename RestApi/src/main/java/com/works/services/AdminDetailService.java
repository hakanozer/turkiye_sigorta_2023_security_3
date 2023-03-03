package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Role;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminDetailService implements UserDetailsService {

    final AdminRepository adminRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameEqualsIgnoreCase(username);
        if (optionalAdmin.isPresent()) {
            Admin adm = optionalAdmin.get();
            return new User(
                  adm.getUsername(),
                  adm.getPassword(),
                  adm.getEnable(),
                  true,
                  true,
                  true,
                  parseRoles( adm.getRoles() )
            );
        }else {
            throw new UsernameNotFoundException("Not Found");
        }
    }

    private Collection<? extends GrantedAuthority> parseRoles(List<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for( Role item : roles ) {
            ls.add(new SimpleGrantedAuthority(item.getName()));
        }
        return ls;
    }

    public ResponseEntity register(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameEqualsIgnoreCase(admin.getUsername());
        Map map = new LinkedHashMap();
        if (optionalAdmin.isPresent()) {
            map.put("status", false);
            map.put("message", "This username in use");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }else {
            admin.setPassword( passwordEncoder.encode( admin.getPassword()) );
            adminRepository.save(admin);
            map.put("status", true);
            map.put("result", admin);
            return new ResponseEntity(map, HttpStatus.OK);
        }
    }

}
