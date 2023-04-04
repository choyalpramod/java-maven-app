def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    echo "${params.VERSION}"
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t choyalpramod/demo-app:jma-${params.VERSION} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push choyalpramod/demo-app:jma-${params.VERSION}"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
