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
        // stage("build jar") {
        //     steps {
        //         script {
        //             gv.buildJar()
        //         }
        //     }
        // }
        // stage("build image") {
        //     steps {
        //         script {
        //             gv.buildImage()
        //             echo "${env.IMAGE_NAME}"
        //         }
        //     }
        // }
        // stage("deploy") {
        //     steps {
        //         script {
        //             gv.deployApp()
        //         }
        //     }
        // }
        stage('commit version update') {
            steps {
                script {
                    withCredentials([sshUserPrivateKey(credentialsId: "choyalpramod-github", keyFileVariable: 'SSH_KEY')]) {
                        sh 'git config --global user.email "jenkins@example.com"'
                        sh 'git config --global user.name "Jenkins"'
                        sh 'git branch'
                        sh 'git status'
                        sh 'git config --list'
                        // sh 'GIT_SSH_COMMAND = "ssh -i $SSH_KEY"
                        sh "git remote set-url origin git@github.com:choyalpramod/java-maven-app.git"
                        sh 'git add .'
                        sh 'git commit -m "ci: version upgrade"'
                        sh 'GIT_SSH_COMMAND = "ssh -i $SSH_KEY"'
                        sh 'git push origin HEAD:jenkins-jobs'
                    }
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
