 package service;




 import com.dbc.colabore.dto.UsuarioCreateDTO;
 import com.dbc.colabore.entity.UsuarioEntity;
 import com.dbc.colabore.repository.UsuarioRepository;
 import com.dbc.colabore.service.UsuarioService;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.junit.Test;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.runner.RunWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;
 import org.mockito.junit.MockitoJUnitRunner;
 import static org.mockito.Mockito.*;

 @RunWith(MockitoJUnitRunner.class)
 public class UsuarioServiceTest {




     @InjectMocks
     private UsuarioService usuarioService;

     @Mock
     private UsuarioRepository usuarioRepository;


     @Mock
     private ObjectMapper objectMapper;

     @BeforeEach
     public void beforeEach(){
         MockitoAnnotations.openMocks(this);
     }


     @Test
      public void createUsuario() throws Exception {
         usuarioService = mock(UsuarioService.class);
         UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
         usuarioCreateDTO.setEmail("tiago.coelho@dbccompany.com.br");
         usuarioCreateDTO.setNome("tiago coelho");
         usuarioCreateDTO.setSenha("123");
         UsuarioEntity usuarioEntity = new UsuarioEntity();
         usuarioService.create(usuarioCreateDTO);
         verify(usuarioService, Mockito.times(1)).create(usuarioCreateDTO);
     }

     @Test
     public void updateUsuario() throws Exception {
         usuarioService = mock(UsuarioService.class);
         UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
         usuarioCreateDTO.setEmail("Eduardo@dbccompany.com.br");
         usuarioCreateDTO.setNome("Eduardo");
         usuarioCreateDTO.setSenha("123");

         UsuarioEntity usuarioEntity = new UsuarioEntity();
         usuarioService.update(usuarioCreateDTO);
         verify(usuarioService, Mockito.times(1)).update(usuarioCreateDTO);
     }

 }
