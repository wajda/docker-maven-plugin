#!/usr/bin/groovy
def repo(){
 return 'wajda/docker-maven-plugin'
}

def stage(){
  return stageProject{
    project = repo()
    useGitTagForNextVersion = true
  }
}

def approveRelease(project){
  def releaseVersion = project[1]
  approve{
    room = null
    version = releaseVersion
    console = null
    environment = 'fabric8'
  }
}

def release(project){
  releaseProject{
    stagedProject = project
    useGitTagForNextVersion = true
    helmPush = false
    groupId = 'com.github.wajda'
    githubOrganisation = 'wajda'
    artifactIdToWatchInCentral = 'docker-maven-plugin'
    artifactExtensionToWatchInCentral = 'jar'
  }
}

def mergePullRequest(prId){
  mergeAndWaitForPullRequest{
    project = repo()
    pullRequestId = prId
  }

}
return this
