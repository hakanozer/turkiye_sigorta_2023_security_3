package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    final AdminRepository adminRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
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
