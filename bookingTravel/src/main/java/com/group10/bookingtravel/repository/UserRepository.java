package com.group10.bookingtravel.repository;

import com.group10.bookingtravel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select distinct new com.group10.bookingtravel.entity.User(u.id,u.username,u.fullname,u.email,u.address,u.dob,u.gender,u.password,u.createdDate,u.status,u.rank,u.totalOrder,u.isAdmin) from User u where ((lower(u.username) like lower(concat('%',?2,'%')))or(?2 = '' )) and ((lower(u.rank) like lower(concat('%',?3,'%')))or(?3 = '' )) and ((u.status = ?4 )or(?4 = 2)) and ((u.id = ?1 )or(?1 = 0))")
    public Optional<List<User>> userList(Long id, String username, String rank, Integer status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user u set u.status = ?2 where u.id = ?1",nativeQuery = true)
    public void updateStatus(Long id, Integer status);

    public Optional<User> getUserByUsername(String username);

    User findByUsername(String username);

    @Modifying
    @Query(value = "insert into user (address, created_date, dob, email, fullname, gender, password, username) VALUES (:address, :created_date, :dob, :email, :fullname, :gender, :password, :username)", nativeQuery = true)
    @Transactional
    void save(@Param("address") String address, @Param("created_date") String created_date, @Param("dob") String dob, @Param("email") String email, @Param("fullname") String fullname, @Param("gender") String gender, @Param("password") String password, @Param("username") String username);
}
