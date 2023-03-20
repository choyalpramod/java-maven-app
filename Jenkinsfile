def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ["1.2.0", "1.3.0", "1.4.0"], description: 'Version')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Execute Test')
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
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    env.ENV = input message: "Select a env to deploy", ok: "Done", parameters: [choice(name: 'ONE', choices: ["staging", "qa", "prod"], description: "choose env")]
                    echo "env ${ENV}"
                    gv.deployApp()
                }
            }
        }
    }   
}
