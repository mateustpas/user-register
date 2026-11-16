package dev.mateustpas.user_register.business;

import dev.mateustpas.user_register.business.converter.UsuarioConverter;
import dev.mateustpas.user_register.business.dto.UsuarioDTO;
import dev.mateustpas.user_register.infrastructure.entity.Usuario;
import dev.mateustpas.user_register.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
