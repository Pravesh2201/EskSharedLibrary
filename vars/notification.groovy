def call() {
    stage('notify') {
          emailext body: 'success', subject: 'Status', to: 'praveshgangwar04@gmail.com'
          slackSend channel: '#jenkins-notification', message: "Build Successful: ${env.JOB_NAME} [${env.BUILD_NUMBER}]"
    }
}
