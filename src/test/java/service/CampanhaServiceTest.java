 package service;



 import com.dbc.colabore.dto.CampanhaCreateDTO;
 import com.dbc.colabore.dto.CategoriaCreateDTO;
 import com.dbc.colabore.dto.CategoriaCreateDTOComNome;
 import com.dbc.colabore.dto.CategoriaDTO;
 import com.dbc.colabore.entity.CampanhaEntity;
 import com.dbc.colabore.entity.CategoriaEntity;
 import com.dbc.colabore.repository.CampanhaRepository;
 import com.dbc.colabore.repository.CategoriaRepository;
 import com.dbc.colabore.service.CampanhaService;
 import com.dbc.colabore.service.CategoriaService;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.junit.Test;
 import org.junit.jupiter.api.BeforeEach;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;
 import java.math.BigDecimal;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.util.HashSet;
 import java.util.Set;

 import static org.mockito.Mockito.*;



 public class CampanhaServiceTest {



     @InjectMocks
     private CategoriaService categoriaService;


     @InjectMocks
     private CampanhaService campanhaService;


     @Mock
     private CampanhaRepository campanhaRepository;

     @Mock
     private CategoriaRepository categoriaRepository;


     @Mock
     private ObjectMapper objectMapper;

     @BeforeEach
     public void beforeEach(){
         MockitoAnnotations.openMocks(this);
     }

     @Test
     public void createCampanha() throws Exception {
         campanhaService = mock(CampanhaService.class);
         CampanhaCreateDTO campanhaCreateDTO = new CampanhaCreateDTO();

         categoriaService = mock(CategoriaService.class);
         CategoriaCreateDTOComNome categoriaCreateDTO = new CategoriaCreateDTOComNome();
         categoriaCreateDTO.setNome("Doação");
         CategoriaEntity categoriaEntity = new CategoriaEntity();
         CategoriaDTO categoriaDTO = categoriaService.create(categoriaCreateDTO);
         Set<CategoriaCreateDTO> categoriaCreateDTOSet = new HashSet<>();
         categoriaCreateDTOSet.add(categoriaDTO);

         campanhaCreateDTO.setCategorias(categoriaCreateDTOSet);
         campanhaCreateDTO.setDescricaoCampanha("Arrecadação de cestas básicas para comunidades carentes do interior de Águas Claras");
         campanhaCreateDTO.setTituloCampanha("Fim de ano solidário");
         campanhaCreateDTO.setConcluiCampanhaAutomaticamente(true);
         campanhaCreateDTO.setMetaArrecadacao(new BigDecimal(15000));
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDate ld = LocalDate.parse("31/12/2022", formatter);
         campanhaCreateDTO.setDataLimiteContribuicao(ld);
         CampanhaEntity campanhaEntity = new CampanhaEntity();
         campanhaService.create(campanhaCreateDTO);
         verify(campanhaService, Mockito.times(1)).create(campanhaCreateDTO);
     }

     @Test
     public void updateCampanha() throws Exception {
         campanhaService = mock(CampanhaService.class);
         CampanhaCreateDTO campanhaCreateDTO = new CampanhaCreateDTO();

         categoriaService = mock(CategoriaService.class);
         CategoriaCreateDTOComNome categoriaCreateDTO = new CategoriaCreateDTOComNome();
         categoriaCreateDTO.setNome("Doação");
         CategoriaEntity categoriaEntity = new CategoriaEntity();
         CategoriaDTO categoriaDTO = categoriaService.create(categoriaCreateDTO);
         Set<CategoriaCreateDTO> categoriaCreateDTOSet = new HashSet<>();
         categoriaCreateDTOSet.add(categoriaDTO);

         campanhaCreateDTO.setCategorias(categoriaCreateDTOSet);
         campanhaCreateDTO.setDescricaoCampanha("Arrecadação de cestas básicas ");
         campanhaCreateDTO.setTituloCampanha("Fim de ano solidário");
         campanhaCreateDTO.setConcluiCampanhaAutomaticamente(true);
         campanhaCreateDTO.setMetaArrecadacao(new BigDecimal(25000));
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDate ld = LocalDate.parse("31/12/2023", formatter);
         campanhaCreateDTO.setDataLimiteContribuicao(ld);
         CampanhaEntity campanhaEntity = new CampanhaEntity();
         campanhaEntity.setIdCampanha(2);
         campanhaService.update(2,  campanhaCreateDTO);
         verify(campanhaService, Mockito.times(1)).update(2,  campanhaCreateDTO);
     }



 }
