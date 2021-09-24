package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.Address;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.domain.UserStatus;
import com.example.Mini.Online.Market.util.SecurityConstants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String s);

    List<User> findUsersByStatus(UserStatus status);

    @Query("select a from Address a where a.user.username=:username")
    List<Address> findAddresses(String username);

    @Query("select u from User u where u.role = " + SecurityConstants.ROLE_SELLER)
    List<User> findAllSellers();

    @Query("select u from User u where u.status = 1 and u.role = " + SecurityConstants.ROLE_SELLER)
    List<User> findPendingSellers();
}
