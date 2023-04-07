def gv

pipeline {
    agent any
    tools {
        maven "maven-3.6"
    }
    // parameters{
    //     string(name: 'VERSION', defaultValue: '1.1.0', description: 'version to deploy in prod')
    // }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("increment version") {
            steps {
                script {
                    echo "Incrementing App Version"
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    echo "${matcher}"
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "${version}-${env.BUILD_NUMBER}"
                    echo "$version-$BUILD_NUMBER"
                    echo "${version}-${env.BUILD_NUMBER}" 
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
                    echo "${env.IMAGE_NAME}"
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
