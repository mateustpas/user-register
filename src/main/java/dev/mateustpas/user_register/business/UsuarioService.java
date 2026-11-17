package dev.mateustpas.user_register.business;

import dev.mateustpas.user_register.business.converter.UsuarioConverter;
import dev.mateustpas.user_register.business.dto.UsuarioDTO;
import dev.mateustpas.user_register.infrastructure.entity.Usuario;
import dev.mateustpas.user_register.infrastructure.exceptions.ConflictException;
import dev.mateustpas.user_register.infrastructure.exceptions.ResourceNotFoundException;
import dev.mateustpas.user_register.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    //DEPENDÊNCIAS
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        verificaEmailRepetido(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void verificaEmailRepetido(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado: " + email);  //Usando o construtor com o Throwable
            }
        }
        catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause()); //Usando o construtor sem o Throwable
        }
    }  //CAPTURA EXCEÇÃO CASO HAJA EMAIL REPETIDO

    public boolean verificaEmailExistente(String email) {

        return usuarioRepository.existsByEmail(email);
    }  //VERIFICA SE O EMAIL EXISTE NO BANCO DE DADOS

    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email));
    }

    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
