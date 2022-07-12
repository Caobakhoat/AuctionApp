package com.example.demo_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;


@Entity()
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "address",nullable = false)
    private String address;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "dob",nullable = false)
    private String dob;
    @Column(name = "nameAvatar",nullable = false)
    private String nameAvatar;
    @Column(name = "role",nullable = false)
    private String role;
    @Column(name = "balance",nullable = false)
    private int balance;
    @Column(name = "isDelete",nullable = false)
    private int isDelete;
    @Column(name = "creatAt")
    @CreationTimestamp
    private LocalDateTime creatAt;
    @Column(name = "modifyAt")
    @UpdateTimestamp
    private LocalDateTime modifyAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userBids")
    private List<Bids> listBidUser;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCreatAuction")
    private List<Auction> listAuctionCreated;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTransaction")
    private List<Transaction> listTransactionUser;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.getRole()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Transient
    public String getPhotosImagePath() {
        if (nameAvatar == null ) return null;

        return "http://localhost:8080/user/imageUser/" + id + "/" + nameAvatar;
    }
}