package com.dbc.colabore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity(name = "USUARIO")
public class UsuarioEntity  implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "usuario_Perfil",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private List<PerfilEntity> perfil;


    @OneToMany(mappedBy="usuarioEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DoacaoEntity> doacoes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_foto_perfil", referencedColumnName = "id_foto_perfil")
    private FotoPerfilEntity fotoPerfil;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (PerfilEntity perfilEntity : perfil) {
            grantedAuthorities.addAll(perfil);
//        }
        return grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
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
}
