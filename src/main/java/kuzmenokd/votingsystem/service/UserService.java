package kuzmenokd.votingsystem.service;

import kuzmenokd.votingsystem.AuthorizedUser;
import kuzmenokd.votingsystem.model.User;
import kuzmenokd.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public User getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repository.getByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User " + name + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
