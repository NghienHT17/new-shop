package com.example.newshop.config;

import com.example.newshop.model.User;
import com.example.newshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataUserConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if(userRepository.findByEmail("admin@gmail.com")== null){
//            userRepository.save(new User("ADMIN"));
//        }
        //Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setUserId(1);
            admin.setFirstName("Nghien");
            admin.setLastName("Hoang");
            admin.setActive(1);
            admin.setRole("ADMIN");
            userRepository.save(admin);//nhớ phải save lại ms dc

        }
        if (userRepository.findByEmail("manager@gmail.com") == null) {
            User manager = new User();
            manager.setEmail("manager@gmail.com");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setUserId(2);
            manager.setFirstName("Manager");
            manager.setLastName("Nguyen");
            manager.setActive(1);
            manager.setRole("MANAGER");
            userRepository.save(manager);//

        }
        if (userRepository.findByEmail("member@gmail.com") == null) {
            User member = new User();
            member.setEmail("member@gmail.com");
            member.setPassword(passwordEncoder.encode("member123"));
            member.setUserId(5);
            member.setFirstName("A");
            member.setLastName("Lê");
            member.setActive(1);
            member.setRole("MEMBER");
            userRepository.save(member);//nhớ phải save lại ms dc

        }
    }
}
