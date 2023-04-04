def buildJar() {
    echo "building the application..."
    // sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    echo "${params.VERSION}"
    // withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
    //     sh 'docker build -t choyalpramod/demo-app:jma-2.0 .'
    //     sh "echo $PASS | docker login -u $USER --password-stdin"
    //     sh 'docker push choyalpramod/demo-app:jma-2.0'
    // }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
