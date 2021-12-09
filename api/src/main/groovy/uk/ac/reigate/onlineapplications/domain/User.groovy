package uk.ac.reigate.onlineapplications.domain

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import com.fasterxml.jackson.annotation.JsonIgnore

import groovy.transform.EqualsAndHashCode

@Entity
@Table(name="`user`", schema = "ONLINEAPPLICATION")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
])
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class User extends BaseEntityIdentity implements UserDetails {

    @Column
    String username

    @Column
    @JsonIgnore
    private String password

    @Column(columnDefinition = "uniqueidentifier")
    private String confirmationCode = UUID.randomUUID().toString()
    
    @Column
    private Date confirmationSent
    
    @Column(columnDefinition = "BIT DEFAULT (0)")
    private boolean confirmed = false
    
    @Column
    private Integer resetPasswordFlag
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = 'student_id', foreignKey = @ForeignKey(name = "FK_user__student"))
    private Application application
    
    @Column
    private Date receiptSent
     
    /**
     * Default NoArgs constructor
     */
    User() {}
    
    /**
     * This constructor is used to create a user from the username and password supplied. 
     * 
     * @param username a username to use for the User.
     * @param password a password to use for the User.
     */
    User(String username, String password) {
        this.username = username
        this.password = password
        //this.confirmationCode
    }
    
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ["USER"].collect { it ->
            new SimpleGrantedAuthority(it)
        }
    }

    @Override
    public String getPassword() {
        return password
    }
    
    public void setPassword(String password) {
        this.password = password
    }

    @Override
    public String getUsername() {
        return username
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.confirmed
    }
}
