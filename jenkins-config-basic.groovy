#!groovy

import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule

println("--- Disabling setup wizard")
def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
instance.setSecurityRealm(hudsonRealm)
hudsonRealm.createAccount("admin", "admin")

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)
instance.save()

Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)

// set timezone
println("--- Setup timezone")
System.setProperty('org.apache.commons.jelly.tags.fmt.timeZone', 'Europe/Bratislava') 



// def user = inst.getSecurityRealm().createAccount("admin", "admin")
// user.save()
