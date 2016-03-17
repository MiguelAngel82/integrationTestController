package integrationtestcontroller


import grails.test.mixin.integration.Integration
import grails.transaction.*
import grails.util.GrailsWebMockUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import spock.lang.*

@Integration
@Rollback
class TestControllerIntegrationSpec extends Specification {

    @Autowired TestController controller
    @Autowired WebApplicationContext ctx

    void setup() {
        //Whithout mocking web request the excpetion trhown is:
        //java.lang.IllegalStateException: No thread-bound request found: Are you referring to request attributes outside of an actual web request, or processing a request outside of the originally receiving thread? If you are actually operating within a web request and still receive this message, your code is probably running outside of DispatcherServlet/DispatcherPortlet: In this case, use RequestContextListener or RequestContextFilter to expose the current request.
        GrailsWebMockUtil.bindMockWebRequest(ctx)
    }

    void cleanup() {
        RequestContextHolder.resetRequestAttributes()
    }

    //This test does not pass
    void "render the template"() {
        when:"calling the index action"
        def response = controller.index()

        then: "modelAndView is empty"
        controller.modelAndView

        and: "the response is empty too"
        response
    }

    //This test pass
    void "render the view"() {
        when:"calling the index action"
        controller.renderView()

        then: "modelAndView is not empty"
        controller.modelAndView

        and: "modelAndView has the view and the specified model"
        controller.modelAndView.viewName == '/test/testView'
        controller.modelAndView.model.parameterOne == 'value of parameter one'
        controller.modelAndView.model.parameterTwo == 'value of parameter two'
    }

}

