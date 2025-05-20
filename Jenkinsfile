pipeline {
    agent any

    tools {
        jdk 'JDK 21'
        maven 'Maven 3.8.1'
    }

    options {
        timeout(time: 60, unit: 'MINUTES')
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'mvn dependency:resolve'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            cucumber buildStatus: 'UNSTABLE',
                    failedFeaturesNumber: 1,
                    failedScenariosNumber: 1,
                    failedStepsNumber: 1,
                    fileIncludePattern: '**/cucumber.json',
                    jsonReportDirectory: 'target/cucumber-reports',
                    sortingMethod: 'ALPHABETICAL'

            junit '**/TEST-*.xml'

            archiveArtifacts artifacts: 'target/**/cucumber*.json', allowEmptyArchive: true

            // If you're generating HTML reports
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/cucumber-reports',
                reportFiles: 'index.html',
                reportName: 'API Test Report'
            ])
        }
    }
}