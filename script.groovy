def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    echo "${env.IMAGE_NAME}"
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t choyalpramod/demo-app:${env.IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push choyalpramod/demo-app:${env.IMAGE_NAME}"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
