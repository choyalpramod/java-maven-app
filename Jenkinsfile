def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ["1.2.0", "1.3.0", "1.4.0"], description: '')
        boolean(name: 'executeTests', defaultValue: true, description: '')
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
            when {
                expression {
                    params.executeTests == true
                }
            }
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
                    echo "deploying"
                    echo '${params.VERSION}'
                    echo `version: ${params.VERSION}`
                    echo "version: ${params.VERSION}"
                    echo params.VERSION
                    echo "version: " + params.VERSION
                    //gv.deployApp()
                }
            }
        }
    }   
}
