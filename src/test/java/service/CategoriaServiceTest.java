 package service;


 import com.dbc.colabore.dto.CategoriaCreateDTOComNome;
 import com.dbc.colabore.entity.CategoriaEntity;
 import com.dbc.colabore.repository.CategoriaRepository;
 import com.dbc.colabore.service.CategoriaService;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.junit.Test;
 import org.junit.jupiter.api.BeforeEach;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;


 import static org.mockito.Mockito.*;



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


 }
