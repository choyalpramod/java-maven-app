#!/usr/bin/env groovy

@Library('jenkins-shared-library')_
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
                    buildJar("choyalpramod/demo-app:jma-${params.VERSION}")
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    // gv.buildImage()
                    buildImage()
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
