import com.xero.EnvironmentVariableLoader

def call(fileName = 'build.properties') {
    def loader = new EnvironmentVariableLoader(this)
    loader.call(fileName)
}
