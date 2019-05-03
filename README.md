# 320-S19-Track1
Integration Track 1's repo for all five teams use.

How to Run Members Only REST service.
Members Only requires a server to host the RESTful API at a URL to receive HTTP POST requests. You can do that at home.

1. Install Apache Tomcat: http://tomcat.apache.org/ is the URL where you can download Tomcat on. Tomcat was used for its simplicitiy in hosting Java Code on a server. The default location of services hosted is localhost:8080.

2. Install Eclipse Java EE: The Java EE edition of Eclipse allows for seamless integration with Tomcat and allows us to easily create and host a Dynamic Web Project folder structure, which is what type of project we are using.

3. Pull from the Demo repo: This hosts the finalized code seen in our demo for the project. Make sure to import your project into your Eclipse workspace and update the dependencies of your build path with the included Jars in the WebContent foflder. Also make sure that your folder is being intepreted by Eclipse as a Dynamic Web Project so that it can be hosted on a Tomcat server.

4. Get ngrok and use its port forwarding: Go to ngrok.com and follow the instructions to port forward to your Tomcat server. You will now be able to run URL requests using the Ngrok link.

5. Install mySQL 


