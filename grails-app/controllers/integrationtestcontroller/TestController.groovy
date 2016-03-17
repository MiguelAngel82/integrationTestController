package integrationtestcontroller

class TestController {

    def index() {
        render template: 'testTemplate', model: [parameterOne: 'value of parameter one', parameterTwo: 'value of parameter two']
    }

    def renderView() {
        render view: 'testView', model: [parameterOne: 'value of parameter one', parameterTwo: 'value of parameter two']
    }
}
