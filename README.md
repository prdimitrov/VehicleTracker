# VehicleTracker Application

## Example queries

### 1. Remove all data from database for owner with EGN 5112072341
**DELETE query:**
http://localhost:8080/api/persons?egn=5112072341

### 2. Retrieve violation types and the number of violations of each type committed in 2002
**GET request:**
http://localhost:8080/api/violations/stats?year=2002

### 3. Retrieve the names and addresses of all owners of red Ferrari cars
**GET request:**
http://localhost:8080/api/owners?model=ferrari&color=червен

### 4. Retrieve a list of violations (act number, violation type, date, and offender name) committed in 2002
**GET request:**
http://localhost:8080/api/violations?year=2002
