@Library("EskSharedLibrary") _
pipeline {
    agent any
    parameters {
        string(name: 'PUBLIC_IP', defaultValue: '', description: 'Public IP of the instance')
        string(name: 'PRIVATE_IP', defaultValue: '', description: 'Private IP of the instance')
    }
    stages {
        stage('clone repository') {
            steps {
                ansible_check()
            }
        }
        stage('approval_user') {
            steps {
                approval_user()
            }
        }
        stage('playbook execution') {
            steps {
                Playbook_Execution()
            }
        }
    }
}
