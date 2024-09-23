def call() {
    stage('approval user') {

     input message: 'Do you want to proceed with the Ansible playbook execution?', ok: 'Proceed'
    }
}
