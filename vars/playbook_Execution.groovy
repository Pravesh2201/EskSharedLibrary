def call() {
    stage('playbook execution') {
        sh """
        ansible-playbook playbook.yml -i hosts \
        --extra-vars "PUBLIC_IP=${params.PUBLIC_IP} PRIVATE_IP=${params.PRIVATE_IP}"
        """
    }
}
