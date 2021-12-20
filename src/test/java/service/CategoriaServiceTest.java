 package service;


 import com.dbc.colabore.dto.CategoriaCreateDTOComNome;
 import com.dbc.colabore.entity.CategoriaEntity;
 import com.dbc.colabore.repository.CategoriaRepository;
 import com.dbc.colabore.service.CategoriaService;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.junit.Test;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.runner.RunWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;
 import org.mockito.junit.MockitoJUnitRunner;

 import java.util.Optional;

 import static org.mockito.Mockito.*;

 @RunWith(MockitoJUnitRunner.class)
 public class CategoriaServiceTest {




     @InjectMocks
     private CategoriaService categoriaService;

     @Mock
     private CategoriaRepository categoriaRepository;


     @Mock
     private ObjectMapper objectMapper;

     @BeforeEach
     public void beforeEach(){
         MockitoAnnotations.openMocks(this);
     }

     @Test
     public void createCategoria() throws Exception {
         categoriaService = mock(CategoriaService.class);
         CategoriaCreateDTOComNome categoriaCreateDTO = new CategoriaCreateDTOComNome();
         categoriaCreateDTO.setNome("Doação");
         CategoriaEntity categoriaEntity = new CategoriaEntity();
         categoriaService.create(categoriaCreateDTO);
         verify(categoriaService, Mockito.times(1)).create(categoriaCreateDTO);
     }


     @Test
     public void deletaCategoriaComSucesso() throws Exception {
         CategoriaEntity categoriaEntity = new CategoriaEntity();
         doReturn(Optional.of(categoriaEntity)).when(categoriaRepository).findById(1);
         categoriaService.delete(1);
         verify(categoriaRepository, times(1)).delete(categoriaEntity);
     }

 }
