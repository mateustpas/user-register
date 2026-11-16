package dev.mateustpas.user_register.infrastructure.repository;

import dev.mateustpas.user_register.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

    //O Transactional ajuda a não causar nenhum erro ao deletar!
    @Transactional
    void deleteByEmail(String email);
}
