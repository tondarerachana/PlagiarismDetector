package controllersTest;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.team208.controllers.MainController;

import com.team208.domain.UserEntity;
import com.team208.domain.UserRepository;
import com.team208.jsonresponse.LoginJsonBean;
import com.team208.jsonresponse.LoginResponse;
import com.team208.jsonresponse.StatusBean;
import com.team208.userservice.UserServiceImpl;

@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = MainController.class)
@PowerMockIgnore({"javax.management.*","org.mockito.*", "org.apache.http.*", "org.apache.http.conn.ssl.*", "javax.net.ssl.*" , "javax.crypto.*"})
public class MainControllerTest{

	private MockMvc mockMvc;
	


	@Mock
	private UserEntity userMockDao;
	
	@Mock
	private UserRepository repo;
	
	@Mock
	private UserServiceImpl userService;
	
	@Mock
	private MainController mainController;
	
	@Before
	public void init() {
//		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}
	
	@Test
	public void testLogin() throws Exception {
		userMockDao = PowerMockito.mock(UserEntity.class);
		repo = PowerMockito.mock(UserRepository.class);
		userService = PowerMockito.mock(UserServiceImpl.class);
		Long userId = (long) 222222;
		LoginJsonBean loginJson = new LoginJsonBean();
		loginJson.setUserId(userId);
		loginJson.setPassword("pass");
		 
		UserEntity u = new UserEntity(userId, "Vihas", "Student", "passowrd", "tondarerachana1@gmail.com");

		LoginResponse user = new LoginResponse();
		StatusBean status = new StatusBean();
		status.setStatus("success");
		status.setStatusCode(200);
		user.setStatus(status);

		PowerMockito.when(repo.findByNEUId(userId)).thenReturn(u);	
		 mockMvc.perform(
				get("/team208/findStudent").param("userId", "222222")).andExpect(status().is(200)).andReturn();

		
		
	}

}
