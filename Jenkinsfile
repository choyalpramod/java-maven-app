def gv

pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                script {
                    echo "building jar"
                    //gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                    //gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying to master."
                    //gv.deployApp()
                }
            }
        }
    }   
}
