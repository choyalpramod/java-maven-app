#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@master', retriever: modernSCM([
    $class: 'GitSCMSource',
    remote: 'https://github.com/choyalpramod/jenkins-shared-library.git',
    credentialsId: 'choyalpramod-git'
]) 
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
                    // gv.buildJar()
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    // gv.buildImage()
                    def imageName = "choyalpramod/demo-app:jma-${params.VERSION}"
                    buildImage(imageName)
                    dockerLogin()
                    dockerPush(imageName)
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
