# Checking the hit point in the area.

- Developed with Play framework, Aurelia, Postgresql.
- Simple registration added.
- Postgresql as a database.
- All passwords saved as a checksum.
- REST API.

Start the app:

- 1)type your database adress in conf/application.conf.
- 2)make sure that your database adress is avaible.
- 3)cd au
- 4)change deploy "dep" script for you cases of foldiers plasement
	- dep is used for deploy aurelia project into play and substituting aurelia
file paths(in vendor-bundle.js) to correct (which is used by play)
- 5)./dep
- 6)cd ../
- 7)./connect 
	- connect is used for connect to the port, which your database used
- 6)activator run
- 7)compile -> run the app -> go to localhost:9000

Register page:

  ![alt tag](https://github.com/yashin-alexander/Play-Aurelia-Postgresql-project/blob/master/screens/Screenshot%20from%202017-09-06%2012-38-07.png?raw=true)
  
Main page:
  ![alt tag](https://github.com/yashin-alexander/Play-Aurelia-Postgresql-project/blob/master/screens/Screenshot%20from%202017-09-07%2000-39-48.png?raw=true)
