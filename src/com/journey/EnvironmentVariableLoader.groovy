package com.xero

class EnvironmentVariableLoader implements Serializable {
    def pipeline

    EnvironmentVariableLoader(pipeline) {
        this.pipeline = pipeline
    }

    def call(fileName) {
        def props = pipeline.readProperties file: fileName
        for (prop in props) {
            pipeline.env[prop.key] = prop.value
        }
    }
}
