# Volgende services zijn beschikbaar:
# http://localhost:7001/test-service/api/v1/RestCall/getTest
# http://localhost:7001/test-service/api/v1/RestCall/create

adminUser='weblogic'
adminPassword='Welkom01'
adminUrl='http://localhost:7001'
# applicationPath='RestTestApplications-ear/target/RestTestApplications-ear-1.0-SNAPSHOT.ear'
applicationPath='RestTestApplication_12212/target/RestTestApplication_12212-1.0-SNAPSHOT.war'
applicationName='RestTestApplication_12212'


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
