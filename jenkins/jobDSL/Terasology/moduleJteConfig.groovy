package Terasology

multibranchPipelineJob('Terasology/ModuleJteConfig') {
    description("Validates the Jenkinsfile for modules")
    branchSources {
        github {
            id('modulejtemultipipe') // IMPORTANT: use a constant and unique identifier
            repoOwner('BSAExperiments')
            repository('ModuleJteConfig')
            checkoutCredentialsId('github-app-terasology-jenkins-io')
            scanCredentialsId('github-app-terasology-jenkins-io')
            buildOriginPRMerge(true) // We want to build PRs in the origin, merge with base branch
            buildOriginBranchWithPR(false) // We don't keep both an origin branch and a PR from it
            buildForkPRHead(false) // We only build forks merged into the base branch
        }
    }
    factory {
        workflowBranchProjectFactory {
            scriptPath("self.Jenkinsfile")
        }
    }
    orphanedItemStrategy {
        defaultOrphanedItemStrategy {
            pruneDeadBranches(true)
            daysToKeepStr("1") // Only keep orphaned PRs and branches for a day (yes, requires a String)
            numToKeepStr("1") // Only keep 1 of the orphans? Unsure what this means exactly
        }
    }
}
