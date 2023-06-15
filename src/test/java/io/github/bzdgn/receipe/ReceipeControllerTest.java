//package io.github.bzdgn.receipe;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.github.bzdgn.receipe.controller.ReceipeController;
//import io.github.bzdgn.receipe.dao.Receipe;
//import io.github.bzdgn.receipe.dao.ReceipeRepository;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@ContextConfiguration(classes = {TestDataSource.class, ReceipeController.class, ReceipeRepository.class})
//public class ReceipeControllerTest {
//
//	private static final ObjectMapper om = new ObjectMapper();
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private ReceipeRepository mockRepository;
//	
//	private List<Receipe> receipes = new ArrayList<>();
//
//	@BeforeEach 
//	public void init() {
//		Receipe receipe1 = mockRepository.save(new Receipe("Hamburger", 1, "Bread, Meat, Onions",
//				"Cook meat on the plate for 5 minutes. Cut onions. Combine the cooked meat and onions in between the breads",
//				false));
//		Receipe receipe2 = mockRepository.save(new Receipe("Cheeseburger", 1, "Bread, Meat, Onions, Cheese",
//				"Cook meat on the plate for 5 minutes. Cut onions. Combine the cooked meat and onions and the slice of cheese in between the breads",
//				false));
//		Receipe receipe3 = mockRepository.save(new Receipe("Vega Sandwich", 2, "4 slices of bread, Mozarella, Cucumber",
//				"Cut mozarella and cucumber into slices. Per serving, use 2 slices of bread and put mozarella and cucumber slices in between",
//				true));
//
//		when(mockRepository.findById(1L)).thenReturn(Optional.of(receipe1));
//		when(mockRepository.findById(2L)).thenReturn(Optional.of(receipe2));
//		when(mockRepository.findById(3L)).thenReturn(Optional.of(receipe3));
//		
//		receipes.add(receipe1);
//		receipes.add(receipe2);
//		receipes.add(receipe3);
//	}
//	
//
//    @Test
//    public void find_allBook_OK() throws Exception {
//        when(mockRepository.findAll()).thenReturn(receipes);
//
//        mockMvc.perform(get("/receipe"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("Hamburger")));
//
//        verify(mockRepository, times(1)).findAll();
//    }
//
//}
