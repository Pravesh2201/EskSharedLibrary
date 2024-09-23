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
                // Clone the repository containing the necessary playbooks for Elasticsearch
                ansible_check()
            }
        }

        stage('approval_user') {
            steps {
                approval_user()
            }
        }

        stage('Prepare Hosts File') {
            steps {
                script {
                    // Create the hosts file using the provided PUBLIC_IP and PRIVATE_IP
                    sh '''
                        echo "[public]" > hosts
                        echo "${PUBLIC_IP} ansible_ssh_private_key_file=/var/lib/jenkins/AWS.pem ansible_user=ubuntu" >> hosts
                        echo "[private]" >> hosts
                        echo "${PRIVATE_IP} ansible_ssh_private_key_file=/var/lib/jenkins/AWS.pem ansible_user=ubuntu" >> hosts
                    '''
                }
            }
        }

        stage('playbook execution') {
            steps {
                Playbook_Execution()
            }
        }

    }
}
