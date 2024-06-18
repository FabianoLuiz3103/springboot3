package br.com.challenge.euroIntegrate.service;

import br.com.challenge.euroIntegrate.model.usuario.Usuario;
import br.com.challenge.euroIntegrate.model.usuario.UsuarioDetailsImpl;
import br.com.challenge.euroIntegrate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        return new UsuarioDetailsImpl(usuario);
    }
}
