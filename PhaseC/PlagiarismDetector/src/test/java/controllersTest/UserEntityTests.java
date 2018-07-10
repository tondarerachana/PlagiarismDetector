package controllersTest;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.ContextConfiguration;



import com.team208.domain.UserEntity;
import com.team208.domain.UserRepository;
import com.team208.jsonresponse.UserJsonBean;

@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = UserRepository.class)
@PowerMockIgnore({"javax.management.*","org.mockito.*", "org.apache.http.*", "org.apache.http.conn.ssl.*", "javax.net.ssl.*" , "javax.crypto.*"})
public class UserEntityTests {


	@Mock
	private UserRepository userMockDao;
	
	@Mock
	private UserEntity userEntity;


	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	
		userMockDao = PowerMockito.mock(UserRepository.class);
	}
	
	@Test()
	public void test1(){ 
		
		Long userId  = (long) 123;
		UserJsonBean user = new UserJsonBean();
		user.setName("rachana");
		user.setEmail("tondarerachana92@gmail.com");
		user.setPassword("pass");
		user.setUserRole("student");
		user.setUserId(userId);
		
		UserEntity u = new UserEntity();
		
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		PowerMockito.when(userMockDao.findByNEUId(userId)).thenReturn(u);	
	}
	
	@Test()
	public void test2(){ 
	
		Long userId  = (long) 123;
		
		
		UserEntity u = new UserEntity();
		
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		PowerMockito.when(userMockDao.findByStudentId(1)).thenReturn(u);	
	}
	
	@Test()
	public void test3(){ 
		
		Long userId  = (long) 123;
		
		UserEntity u = new UserEntity();
		
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		PowerMockito.when(userMockDao.findByEmail("r.t@gmail.com")).thenReturn(u);	
	}
	
	@Test()
	public void test4(){ 
		userMockDao = PowerMockito.mock(UserRepository.class);
		Long userId  = (long) 123;
		
		UserEntity u = new UserEntity();
		
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		assertEquals("rachana", u.getName());
	
	}
	
	@Test()
	public void test5(){ 
		
		Long userId  = (long) 123;
		UserJsonBean user = new UserJsonBean();
		user.setName("rachana");
		user.setEmail("tondarerachana92@gmail.com");
		user.setPassword("pass");
		user.setUserRole("student");
		user.setUserId(userId);
		
		
		assertEquals("rachana", user.getName());
	}
}
