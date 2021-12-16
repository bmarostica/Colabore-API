// package security;




// import com.dbc.colabore.dto.UsuarioCreateDTO;
// import com.dbc.colabore.entity.CategoriaEntity;
// import com.dbc.colabore.entity.UsuarioEntity;
// import com.dbc.colabore.repository.CampanhaRepository;
// import com.dbc.colabore.repository.CategoriaRepository;
// import com.dbc.colabore.repository.UsuarioRepository;
// import com.dbc.colabore.service.CampanhaService;
// import com.dbc.colabore.service.CategoriaService;
// import com.dbc.colabore.service.UsuarioService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.MockitoJUnitRunner;

// import java.util.Optional;


// import static org.mockito.Mockito.*;

// @RunWith(MockitoJUnitRunner.class)
// public class TesteMockito {


//     @Mock
//     private CampanhaRepository campanhaRepository;

//     @InjectMocks
//     private CampanhaService campanhaService;

//     @Mock
//     private UsuarioService usuarioService;

//     @Mock
//     private UsuarioRepository usuarioRepository;

//     @Mock
//     private CategoriaService categoriaService;

//     @Mock
//     private CategoriaRepository categoriaRepository;

//     @Mock
//     private CategoriaEntity categoriaEntity;


//     @Mock
//     private ObjectMapper objectMapper;

//     @BeforeEach
//     public void beforeEach(){
//         MockitoAnnotations.openMocks(this);
//     }

// //    @Test
// //    void verificarIdCampanhaComSucesso(){
// //        campanhaService = mock(CampanhaService.class);
// //        when(campanhaService.verificarIdCampanhaComSucesso(anyInt())).thenCallRealMethod();
// //        Assertions.assertTrue(campanhaService.verificarIdCampanhaComSucesso(1));
// //    }

//     @Test
//      void createUsuario() throws Exception {
//         usuarioService = mock(UsuarioService.class);
//         UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
//         usuarioCreateDTO.setEmail("tiagocoelho.silva@hotmail.com");
//         usuarioCreateDTO.setNome("tiago");
//         usuarioCreateDTO.setSenha("123");
//         UsuarioEntity usuarioEntity = new UsuarioEntity();
//         doReturn(Optional.of(usuarioEntity)).when(usuarioRepository).findById(anyInt());
//         usuarioService.create(usuarioCreateDTO);
//         verify(usuarioService, Mockito.times(1)).create(usuarioCreateDTO);
//     }

//     @Test
//     public void deletaCategoriaComSucesso() throws Exception {
//         CategoriaEntity categoriaEntity = new CategoriaEntity();
//         doReturn(Optional.of(categoriaEntity)).when(categoriaRepository).findById(1);
//         categoriaService.delete(1);
//         verify(categoriaRepository, times(1)).delete(categoriaEntity);
//     }




// }
