def gv

pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                script {
                    echo "building jar"
                    echo "executing ${BRANCH_NAME}"
                    //gv.buildJar()
                }
            }
        }
        stage("build image") {
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    echo "building image"
                    //gv.buildImage()
                }
            }
        }
        stage("deploy") {
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    echo "deploying to master."
                    //gv.deployApp()
                }
            }
        }
    }   
}
