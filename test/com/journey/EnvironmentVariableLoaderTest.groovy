package com.journey

class EnvironmentVariableLoaderTest extends GroovyTestCase {
    // Stubbing the pipeline script
    class PipelineScript {
        def env = [:]
        def properties = [:]

        def readProperties(properties) {
            return this.properties
        }

        def PipelineScript(env, props) {
            this.env = env
            this.properties = props
        }
    }

    void testEnvironmentVariableLoaderWithEmptyEnv() {
        def env = [:]
        def props = [ TEST_PROPERTY: 'apple' ]
        def pipeline = new PipelineScript(env, props)
        def loader = new EnvironmentVariableLoader(pipeline)
        loader.call('test.properties')

        assert loader.getPipeline().env['TEST_PROPERTY'] == 'apple'
    }

    void testEnvironmentVariableLoaderWithExistingEnv() {
        def env = [ TEST_PROPERTY: 'apple' ]
        def props = [ TEST_PROPERTY: 'orange' ]
        def pipeline = new PipelineScript(env, props)
        def loader = new EnvironmentVariableLoader(pipeline)
        loader.call('test.properties')

        assert loader.getPipeline().env['TEST_PROPERTY'] == 'orange'
    }

    void testEnvironmentVariableLoaderBooleanType() {
        def env = [:]
        def props = [ TEST_PROPERTY_BOOL: true ]
        def pipeline = new PipelineScript(env, props)
        def loader = new EnvironmentVariableLoader(pipeline)
        loader.call('test.properties')

        assert loader.getPipeline().env['TEST_PROPERTY_BOOL'] == true
    }
}
