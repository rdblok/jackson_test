# Volgende services zijn beschikbaar:
# http://localhost:7010/test-service/api/v1/RestCall/getTest
# http://localhost:7010/test-service/api/v1/RestCall/create

adminUser='weblogic'
adminPassword='Welkom01'
adminUrl='http://localhost:7010'
# applicationPath='RestTestApplications-ear/target/RestTestApplications-ear-1.0-SNAPSHOT.ear'
applicationPath='RestTestApplication/target/RestTestApplication-1.0-SNAPSHOT.war'
applicationName='RestTestApplication'


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
