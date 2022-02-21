package br.ucsal.fitsys.fitsys.security;

import br.ucsal.fitsys.fitsys.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * Model criado para manipulação de usuário
     */

    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {
    }

    public UserSS(Integer id, String email, String senha, Perfil perfil) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(perfil.getDescricao()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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

    public boolean hasRole(Perfil perfil){
        return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }
}
