Create REST API for personnel monitoring and management in IT company. 
Uses Java 8, MySQL, Spring Boot, Spring Core, Spring Data.

####Examples of requests:
#####Method GET
- Get all people in company: http://your_url/person
- Get one person: http://your_url/person/person_id
- Get all department in company: http://your_url/department
- Get one department: http://your_url/department/department_id
- Get all position in company: http://your_url/position
- Get one position: http://your_url/position/position_id
- Get all project in company: http://your_url/project
- Get one project: http://your_url/project/project_id
#####Method POST
- Create/Add person in company: http://your_url/person
  - Content-Type application-json
  - Body: 
  ```json 
  {
   "name": "Person_name",
   "department": {"id": department_id},
   "position": {"id": position_id},
   "project": {"id": project_id}
   } 
- Create/Add department in company: http://your_url/department
- Content-Type application-json
  - Body: 
  ```json 
  {
   "name": "Department_name",
  } 
- Create/Add position in company: http://your_url/position
- Content-Type application-json
  - Body: 
  ```json 
  {
   "name": "Position_name",
   "department": {"id": department_id},
  } 
- Create/Add project in company: http://your_url/project
- Content-Type application-json
  - Body: 
  ```json 
  {
   "name": "Project_name",
  } 
#####Method PUT
Required item -  id. Others if not in JSON - will not be changed.
- Update/change person: http://your_url/person
  - Content-Type application-json
  - Body: 
  ```json 
  {
   "id": "person_id", 
   "name": "Person_name",
   "department": {"id": department_id},
   "position": {"id": position_id},
   "project": {"id": project_id}
   } 
- Update/change department in company: http://your_url/department
- Content-Type application-json
  - Body: 
  ```json 
  {
   "id": "department_id",
   "name": "Department_name",
  } 
- Update/change position in company: http://your_url/position
- Content-Type application-json
  - Body: 
  ```json 
  {
   "id": "position_id",
   "name": "Position_name",
   "department": {"id": department_id},
  } 
- Update/change project in company: http://your_url/project
- Content-Type application-json
  - Body: 
  ```json 
  {
   "id": "project_id",
   "name": "Project_name",
  }
#####Method DELETE
- Delete one person: http://your_url/person/person_id
- Delete department: http://your_url/department/department_id
- Delete position: http://your_url/position/position_id
- Delete project: http://your_url/project/project_id