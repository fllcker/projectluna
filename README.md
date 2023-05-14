ㅤ<h1 align="center">Project Luna

[![](https://img.shields.io/badge/Developed%20by-fllcker-%236DB33F)](https://github.com/fllcker)
![](https://img.shields.io/badge/JDK-17-%23E76F00)
![](https://img.shields.io/badge/Spring%20Boot-3.0.5-%236DB33F)
[![](https://img.shields.io/badge/DBMS-Postgres-%234476ff)](https://www.postgresql.org/)
</h1>


<p>Project Luna is a project that includes several productivity services, among which are: a calendar, a to-do board. Interestingly, the project uses kafka to provide communication between services, as well as mongodb as a database.</p>


<h1>How to launch</h1>
<ol>
  <li>Run <strong>Kafka</strong> in <strong>Docker</strong> (on port - <strong>29092</strong>)</li>
  <li>Create repo for config-server (from <strong>CONFIG_SERVER_EXAMPLE</strong> dir), set url in yml configuration in config-server</li>
  <li>Configure connection to <strong>Mongo</strong> in these services: auth, groups, invitations, tasks, users, workspaces</li>
  <li>Run in order: config-server, eureka-server, api-gateway, (wait), others</li>
</ol>
