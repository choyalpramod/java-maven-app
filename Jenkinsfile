def gv

pipeline {
    agent any
    tools {
        maven "maven-3.6"
    }
    parameters{
        string(name: 'VERSION', defaultValue: '1.1.0', description: 'version to deploy to prod')
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                    echo "${params.VERSION}"
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    } 
    post {
        always {
            echo "post always"
        }
        success {
            echo "post on success"
        }
        failure {
            echo "post failure"
        }
    }  
}
