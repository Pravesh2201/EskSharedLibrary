def call() {
    stage('playbook execution') {

        sh "ansible-playbook playbook.yml -i hosts"
    }
}
