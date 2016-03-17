package integrationtestcontroller

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TestController)
class TestControllerSpec extends Specification {

    void "render the template"() {
        when:
        controller.index()

        then:
        response.text == '<span>The model rendered is: parameterOne: value of parameter one - parameterTwo: value of parameter two</span>'
    }

    void "render the view"() {
        when:
        controller.renderView()

        then:
        view == '/test/testView'
        model.parameterOne == 'value of parameter one'
        model.parameterTwo == 'value of parameter two'
    }
}
