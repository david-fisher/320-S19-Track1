# 320-S19-Track1
Integration Track 1's repo for all five teams use.

# Frontend Build Setup

``` bash
# install NodeJS
install NodeJS 10.11.0

# clone repository
pull the github repository and cd into the folder

# remove node_modules
delete the node_modules folder if it exists

# install dependencies
npm install
npm install -g vue
npm install -g @vue/cli

#initialize project
vue init webpack AccountOwnerClient
enter "members-only" for your project name
fill in the rest arbitrarily

# serve with hot reload at localhost:8080, observe that everything is correct
npm run dev

# build for production with minification
npm run build
```

# How to Run Members Only REST service.
Members Only requires a server to host the RESTful API at a URL to receive HTTP POST requests. You can do that at home.

1. Install Apache Tomcat: http://tomcat.apache.org/ is the URL where you can download Tomcat on. Tomcat was used for its simplicitiy in hosting Java Code on a server. The default location of services hosted is localhost:8080.

2. Install Eclipse Java EE: The Java EE edition of Eclipse allows for seamless integration with Tomcat and allows us to easily create and host a Dynamic Web Project folder structure, which is what type of project we are using.

3. Pull from the Demo repo: This hosts the finalized code seen in our demo for the project. Make sure to import your project into your Eclipse workspace and update the dependencies of your build path with the included Jars in the WebContent folder. Also make sure that your folder is being intepreted by Eclipse as a Dynamic Web Project so that it can be hosted on a Tomcat server. The only relevant folder for the REST service is the folder titled 'members-only'.

4. Get ngrok and use its port forwarding: Go to ngrok.com and follow the instructions to port forward to your Tomcat server. You will now be able to run URL requests using the Ngrok link.

5. Install mySQLServer for the database and make sure it is running on the localhost:3306.

# mySQL installation guide

This will serve as the guide for installing the required software and getting the database instance up and running for testing preperation. 

## File prep and setup
The first step is downloading the files required to run the DBAdapter and the MySQL Database. 

From the **database_rewrite** branch, only the following files are required: **DBAdapter.java** and **DBTrackOne.sql**. Place those in your src directory. They will be used later.

The DBAdapter requires the use of one external .jar file in order to run: the MySQL wrapper library. This file can be downlaoded [here.](https://downloads.mysql.com/archives/utilities/) Once unzipping these files, the .jar file can be found inside. Add that to your build path.

## Configuring Up MySQL
The second step is downloading MySQL version 8.0. The link to do so can be found [here.](https://dev.mysql.com/downloads/mysql/) Download the recommended installer (this guide assumes you are using Windows). Proceed through the installer, selecting the recommend option for everything you want to download. 

At the end, you will be prompted with a variety of missing dependencies. This is because the recommended install installs a bunch of wrappers and functionality for languages as well as extensions for code editors. If you are missing any of these languages or editors, you will be prompted with an error. **SO LONG AS MySQL OR MySQL WORKBENCH IS NOT IN ERROR, YOU'RE GOOD.** You will then be prompted with the kind of server you wish to run. Select the "classic" option.

Then, head to the install folder in command prompt where MySQL was installed, and head to the **bin** directory. Enter the following commands:

`mysqld --install`
and then
`mysqld --initialize`

When that is complete, open up MySQL Workbench. This is a GUI for MySQL which makes setting stuff up a lot easier. You will initially be prompted for a username and password upon startup. For the purpose of today's demo, we are using "root" for both username and password. 

Once that is set up, open up **run** and run **services.msc**. Find MySQL and enable it as a service (you may have to do this as an administrator.) Then, go back to MySQL Workbench, and attempt to establish a connection to the database by selecting the **Database** tab. In the window that pops up, select the stored connection to **Local Instance MySQL**. Once you're fully connected, open up the TrackOneDB.sql file in Workbench, and run that script. If all goes well, it should now be up and running on your machine.




