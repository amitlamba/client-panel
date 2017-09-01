package com.und.com.und.eventapi

//import com.und.security.controller.UserRestController
import org.scalatest.BeforeAndAfterEach
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

/*

@ContextConfiguration(classes = Array(classOf[ContextConfiguration], classOf[WebAppConfiguration])) // (1)
@WebAppConfiguration
class SampleControllerTest extends ControllerSpec(classOf[UserRestController]) with BeforeAndAfterEach {

  it should "assign categories to a blog post on /blogPosts/1234/addCategory" in {
    mvc.perform(post("/blogPosts/1234/addCategory")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"categoryId\": [\"111\", \"222\"] }"))
      .andExpect(status().isOk())
    verify(blogPostService).addCategoryToBlog("1234", Array("111", "222"))
  }

  override def beforeEach() = {
    super.beforeEach()
    reset(blogPostService) // (5)
  }
}

object ReportApiControllerSpec {
  val blogPostService = Mockito.mock(classOf[BlogPostService]) // (2)

  @Configuration
  class Config {
    @Bean def blogPostServiceBean = blogPostService // (3)
    @Bean def controller = new BlogPostRestController(blogPostService) // (4)
  }
}
*/


