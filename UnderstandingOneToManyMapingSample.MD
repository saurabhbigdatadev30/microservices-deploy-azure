
🏗️ Relationship Representation
    This JSON models a one-to-many relationship : Between Departments and Employees. 
      Each employee belongs to only one department, while each department can have multiple employees.
    
    - One Department → Many Employees
    - Each employee record points to one department via the nested department object.
    - Departments don’t directly list employees here; instead, employees reference their department.

   In the below JSON , 
     - Employee instances [Alice, Bob, and Charlie] belong to the [Engineering department (id: 1)], 
     - Employee instances [Diana and Ethan] belong to [Human Resources (id: 2)]. 
     - Employee instances [Fiona] belongs to [Finance (id: 3)],
     - Employee instances [George] has no department assigned (department is null).
    
     {
      "flag": true,
      "code": 200,
      "message": "Find All Success",
      "data": [
                {
                "id": "E101",
                "name": "Alice",
                "designation": "Software Engineer",
                "email": "alice@example.com",
                "department": {
                "id": 1,
                "name": "Engineering",
                "numberOfEmployees": 3
                }
                },
                {
                "id": "E102",
                "name": "Bob",
                "designation": "QA Analyst",
                "email": "bob@example.com",
                "department": {
                "id": 1,
                "name": "Engineering",
                "numberOfEmployees": 3
                }
                },
                {
                "id": "E103",
                "name": "Charlie",
                "designation": "DevOps Specialist",
                "email": "charlie@example.com",
                "department": {
                "id": 1,
                "name": "Engineering",
                "numberOfEmployees": 3
                }
                },
                {
                "id": "E104",
                "name": "Diana",
                "designation": "HR Manager",
                "email": "diana@example.com",
                "department": {
                "id": 2,
                "name": "Human Resources",
                "numberOfEmployees": 2
                }
                },
                {
                "id": "E105",
                "name": "Ethan",
                "designation": "Recruiter",
                "email": "ethan@example.com",
                "department": {
                "id": 2,
                "name": "Human Resources",
                "numberOfEmployees": 2
                }
                },
                {
                "id": "E106",
                "name": "Fiona",
                "designation": "Finance Analyst",
                "email": "fiona@example.com",
                "department": {
                "id": 3,
                "name": "Finance",
                "numberOfEmployees": 1
                }
                },
                {
                "id": "E107",
                "name": "George",
                "designation": "Intern",
                "email": "george@example.com",
                 "department": null
                 }
     ]
}
