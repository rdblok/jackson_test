# Volgende services zijn beschikbaar:
# http://localhost:7001/RestApplication_1213/api/v1/RestCall/getTest
# http://localhost:7001/RestApplication_1213/api/v1/RestCall/create

adminUser='weblogic'
adminPassword='Welkom01'
adminUrl='http://localhost:7001'
# applicationPath='RestApplication_1213/target/RestApplication_1213-1.0-SNAPSHOT.war'
applicationPath='RestApplications_1213/RestApplications_1213-ear/target/RestApplications_1213-ear-1.0-SNAPSHOT.ear'
applicationName='RestApplication_1213'


connect(adminUser, adminPassword, adminUrl)

app = getMBean('/AppDeployments/' + applicationName)
if(not(app == None)):
	print 'Stopping and undeploying'
	stopApplication(applicationName)
	undeploy(applicationName)
	print 'Undeploy of', applicationName, 'successful'
else:
	print 'Nothing to undeploy'

deploy(applicationName, applicationPath, target='AdminServer' , upload='true')
startApplication(applicationName)

disconnect()
exit()
