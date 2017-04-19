import jenkins.model.*
import java.util.logging.Logger

def configName = "init_15_slack_notifier_config.groovy"
Logger logger = Logger.getLogger("$configName")

def j = Jenkins.getInstance()

File disableScript = new File(j.rootDir, ".disable-$configName")
if (disableScript.exists()) {
    logger.info("DISABLED $configName")
    return
}

logger.info("begin slack notifier config")

def desc = j.getDescriptor("jenkins.plugins.slack.SlackNotifier")

desc.setTeamDomain("beedemo-team")
desc.setRoom("#ci")
desc.setAuthTokenCredentialId("beedemo-slack-token")

desc.save()

logger.info("configured slack notifier")

//create marker file to disable scripts from running twice
disableScript.createNewFile()