package com.br.supercevaja.Super.CevaJa;

import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioEditDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.model.Cargo;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.repository.CargoRepository;
import com.br.supercevaja.Super.CevaJa.repository.UsuarioRepository;
import com.br.supercevaja.Super.CevaJa.service.UsuarioService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CargoRepository cargoRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(usuarioService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCreateComSucesso() throws RegraDeNegocioException {


        UsuarioCreateDto usuarioCreateDto = getUsuarioCreateDto();

        Usuario usuario = getUsuario();

        Cargo cargo = new Cargo();
        cargo.setIdCargo(1);
        cargo.setNome("ROLE_USER");

        UsuarioDto usuarioDto = getUsuarioDto();

        when(passwordEncoder.encode(any(CharSequence.class)))
                .thenReturn("123456");

        when(cargoRepository.findById(any()))
                .thenReturn(Optional.of(cargo));

        when(usuarioRepository.save(any()))
                .thenReturn(getUsuario());


        UsuarioDto usuarioDtoRetorno = usuarioService.criarUsuario(usuarioCreateDto);


        assertNotNull(usuarioDto);
        assertNotNull(usuarioDtoRetorno.getIdUsuario());
        assertEquals(usuario.getIdUsuario(), usuarioDtoRetorno.getIdUsuario());
    }

    @Test
    public void deveTestarBuscarPorUsername() throws RegraDeNegocioException {

        when(usuarioRepository.findByUsername(any()))
                .thenReturn(Optional.of(getUsuario()));

        Usuario usuarioRetorno = usuarioService.buscarUsuarioPorUsername("Ricardo");

        assertNotNull(usuarioRetorno);
    }

    @Test
    public void deveTestarBuscarTodosUsuaris() {
        Usuario usuario1 = getUsuario();
        Usuario usuario2 = getUsuario();
        usuario2.setNome("Raul");
        usuario2.setIdUsuario(2);

        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll())
                .thenReturn(usuarios);

        List<UsuarioDto> listaRetorno = usuarioService.buscarTodosUsuarios();

        assertNotNull(listaRetorno);
        assertTrue(listaRetorno.size() > 0);
        assertEquals(2, listaRetorno.size());


    }

    @Test
    public void deveTestarBuscarPorId() throws RegraDeNegocioException {

        when(usuarioRepository.findById(any()))
                .thenReturn(Optional.of(getUsuario()));

        UsuarioDto usuarioRetorno = usuarioService.buscarUsuarioPorId(1);

        assertNotNull(usuarioRetorno);
        assertEquals(getUsuario().getIdUsuario(), usuarioRetorno.getIdUsuario());
    }

    @Test
    public void deveTestarAlterarPorUserId() throws RegraDeNegocioException {
        Usuario usuario = getUsuario();
        usuario.setNome(getUsuarioEditDto().getNome());


        when(usuarioRepository.findById(any()))
                .thenReturn(Optional.of(getUsuario()));

        when(usuarioRepository.save(any()))
                .thenReturn(usuario);
        UsuarioDto usuarioRetorno = usuarioService.alterarPorUserId(1, getUsuarioEditDto());

        assertNotNull(usuarioRetorno);
        assertEquals(usuarioRetorno.getNome(), getUsuarioEditDto().getNome());
    }

    @Test
    public void deveTestarDeletarPorId() throws RegraDeNegocioException {
        when(usuarioRepository.findById(any()))
                .thenReturn(Optional.of(getUsuario()));
        usuarioService.deletarPorId(1);
    }

    @Test
    public void deveTestarDeletarPorUsername() throws RegraDeNegocioException {

        when(usuarioRepository.findByUsername(any()))
                .thenReturn(Optional.of(getUsuario()));

        usuarioService.deletarPorUsername("José");
    }

    @Test
    public void deveTestarValidarUsuario() throws RegraDeNegocioException {


        when(usuarioRepository.findByUsername(any()))
                .thenReturn(Optional.of(getUsuario()));

        UsuarioDto usuarioRetorno = usuarioService.validarUsuario("Lincoln");

        assertEquals(usuarioRetorno.getDataNascimento(), getUsuario().getDataNascimento());

    }


    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarValidarUsuarioComExceptionUsername() throws RegraDeNegocioException {

        when(usuarioRepository.findByUsername(any()))
                .thenReturn(Optional.empty());

        usuarioService.validarUsuario("Lincoln");

    }

    private static UsuarioDto getUsuarioDto() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Ricardo");
        usuarioDto.setSobrenome("Langbecker");
        usuarioDto.setUsername("Rlangbecker");

        return usuarioDto;
    }

    private static UsuarioCreateDto getUsuarioCreateDto() {
        UsuarioCreateDto usuarioCreateDto = new UsuarioCreateDto();
        usuarioCreateDto.setNome("Ricardo");
        usuarioCreateDto.setSobrenome("Langbecker");
        usuarioCreateDto.setUsername("Rlangbecker");
        usuarioCreateDto.setSenha("123456");

        return usuarioCreateDto;
    }

    private static Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(5);
        usuario.setNome("Ricardo");
        usuario.setSobrenome("Langbecker");
        usuario.setUsername("Rlangbecker");
        usuario.setSenha("123456");
        usuario.setDataNascimento(LocalDate.of(1994, 03, 07));

        return usuario;
    }

    private static UsuarioEditDto getUsuarioEditDto() {
        UsuarioEditDto usuarioEditDto = new UsuarioEditDto();
        usuarioEditDto.setNome("Raul");
        usuarioEditDto.setSobrenome("Seixas");
        return usuarioEditDto;
    }
}
