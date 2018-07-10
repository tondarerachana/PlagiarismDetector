package controllersTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.team208.domain.CourseEntity;
import com.team208.domain.CourseRepository;
import com.team208.domain.UserEntity;
import com.team208.jsonresponse.CourseJsonBean;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*","org.mockito.*", "org.apache.http.*", "org.apache.http.conn.ssl.*", "javax.net.ssl.*" , "javax.crypto.*"})
public class CourseEntityTests {
	
	@Mock
	private CourseRepository courseMockDao;
	
	@Mock
	private CourseEntity courseEntity;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(courseEntity).build();
		courseMockDao = PowerMockito.mock(CourseRepository.class);
	}
	
	@Test()
	public void test1(){ 
	
		CourseEntity course = new CourseEntity();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("SEA");
		course.setCourseName("MSD11");
		course.setSection(1);
		
		UserEntity u = new UserEntity();
		Long userId  = (long) 123;
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		course.setCreatedCourseBy(u);
		
		int cId = 1;
		
		PowerMockito.when(courseMockDao.findByAbbr("CS5300")).thenReturn(cId);
		
	}
	
	@Test()
	public void test2(){ 
		List<Integer> secs = new ArrayList<Integer>();
		secs.add(1);
		
		CourseEntity course = new CourseEntity();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("SEA");
		course.setCourseName("MSD11");
		course.setSection(1);
		course.setCourseTerm("Spring2018");
		UserEntity u = new UserEntity();
		Long userId  = (long) 123;
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		course.setCreatedCourseBy(u);
		

		PowerMockito.when(courseMockDao.findByCourseId(1)).thenReturn(course);
		
	}
	
	
	@Test()
	public void test3(){ 
		CourseEntity course = new CourseEntity();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("SEA");
		course.setCourseName("MSD11");
		course.setSection(1);
		course.setCourseTerm("Spring2018");
		UserEntity u = new UserEntity();
		Long userId  = (long) 123;
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		course.setCreatedCourseBy(u);
		
		CourseEntity course1 = new CourseEntity();
		course.setCourseAbbr("CS7300");
		course.setCourseLoc("SEA");
		course.setCourseName("DSD11");
		course.setSection(1);
		course.setCourseTerm("Spring2018");
		course.setCreatedCourseBy(u);
		
		Set<CourseEntity> setCourses = new HashSet<CourseEntity>();
		setCourses.add(course);
		setCourses.add(course1);

		
		PowerMockito.when(courseMockDao.findByTerm("Spring2018")).thenReturn(setCourses);
		
	}
	
	
	
	@Test()
	public void test4(){ 
		List<String> terms = new ArrayList<String>();
		terms.add("Spring2018");
		
		CourseEntity course = new CourseEntity();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("SEA");
		course.setCourseName("MSD11");
		course.setSection(1);
		course.setCourseTerm("Spring2018");
		UserEntity u = new UserEntity();
		Long userId  = (long) 123;
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		course.setCreatedCourseBy(u);
		
		List<Integer> cId = new ArrayList<Integer>();
		cId.add(1);
		
		PowerMockito.when(courseMockDao.findByTermAndAbbr(terms, "CS5300")).thenReturn(cId);
		
	}
	
	@Test()
	public void test6(){ 

		CourseEntity course = new CourseEntity();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("SEA");
		course.setCourseName("MSD11");
		course.setSection(1);
		course.setCourseTerm("Spring2018");
		UserEntity u = new UserEntity();
		Long userId  = (long) 123;
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		course.setCreatedCourseBy(u);
		
		CourseEntity course1 = new CourseEntity();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("BOS");
		course.setCourseName("DSD11");
		course.setSection(1);
		course.setCourseTerm("Spring2018");
		course.setCreatedCourseBy(u);
		
		Set<CourseEntity> setCourses = new HashSet<CourseEntity>();
		setCourses.add(course);
		setCourses.add(course1);

		
		PowerMockito.when(courseMockDao.findByMultipleCourseAbbr("CS5300")).thenReturn(setCourses);
		
	}
	
	
	@Test()
	public void test7(){ 
		List<Integer> secs = new ArrayList<Integer>();
		secs.add(1);
		
		CourseEntity course = new CourseEntity();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("SEA");
		course.setCourseName("MSD11");
		course.setSection(1);
		course.setCourseTerm("Spring2018");
		UserEntity u = new UserEntity();
		Long userId  = (long) 123;
		u.setEmail("r.t@gmail.com");
		u.setName( "rachana");
		u.setPassword("pass");
		u.setUserRole("Student");
		u.setUserId(userId);
		
		course.setCreatedCourseBy(u);
		
		List<Integer> cId = new ArrayList<Integer>();
		cId.add(1);
		
		PowerMockito.when(courseMockDao.findByAbbrTermSections("CS5300", "Spring2018", secs)).thenReturn(cId);
		
	}
	
	@Test()
	public void test8(){ 
		List<Integer> secs = new ArrayList<Integer>();
		secs.add(1);
		
		CourseJsonBean course = new CourseJsonBean();
		course.setCourseAbbr("CS5300");
		course.setCourseLoc("SEA");
		course.setCourseName("MSD11");
		course.setSections(secs);
		course.setCourseTerm("Spring2018");
		Long userId  = (long) 123;
		course.setCreatedCourseBy(userId);
	
		assertEquals("CS5300", course.getCourseAbbr());
	}
	
	
}
