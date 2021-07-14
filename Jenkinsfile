pipeline {
   agent any

   stages {
      stage('Build') {
         steps {
            bat 'mvn -B compile'
         }
      }
      //Extra addition from here
      stage ('JFrog Upload') {
            steps {
                rtUpload (
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER,
                    serverId: SERVER_ID, // Obtain an Artifactory server instance, defined in Jenkins --> Manage:
                    spec: '''{
                              "files": [
                                 {
                                  "pattern": "$WORKSPACE/Demo-Artifactory/Artifact_*",
                                  "target": "result/",
                                  "recursive": "false"
                                } 
                             ]
                        }'''    
                    )
            }
        }
      stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER,
                    serverId: SERVER_ID
                )

                rtPublishBuildInfo (
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER,
                    serverId: SERVER_ID
                )
            }
        }
      
      //Extra addition ends here
      stage('SonarQube Analysis'){
         steps{
               bat 'mvn clean verify'
               bat '''mvn sonar:sonar \
                  -Dsonar.projectKey=pipeline-test \
                  -Dsonar.host.url=http://localhost:9000 \
                  -Dsonar.login=086a293bb8528f2f968a31ecda1c1c3a9190e4b0'''
         }
      }
      //stage('Archive'){
      //    steps{
      //        archiveArtifacts 'target/*.jar'
      //    }
      //}
      
   } //stages end
   post {
   		  always{
   		  		echo 'Stages completed, Now going to Post section'
   		  }
          success{
          	  echo 'Stages were all good, re-running Cucumber test, please wait'
              //bat 'mvn -B clean install'
              cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
          }
          unsuccessful {
          		echo 'One or more stages failed'
          }
      }
}//pipeline end
