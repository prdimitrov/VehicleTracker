# VehicleTracker Application

## Example queries

* ### Retrieve a list of violations (act number, violation type, date, and offender name) committed in 2002
**GET request:**
http://localhost:8080/api/violations?year=2002

* ### Retrieve the names and addresses of all owners of red Ferrari cars
**GET request:**
http://localhost:8080/api/owners?model=ferrari&color=червен

* ### Retrieve violation types and the number of violations of each type committed in 2002
**GET request:**
http://localhost:8080/api/violations/stats?year=2002

* ### Remove all data from database for owner with EGN 5112072341
**DELETE query:**
http://localhost:8080/api/persons?egn=5112072341

## Database creation with test data
### Create database
CREATE DATABASE `vehicle_tracker`;
### Use vehicle_tracker database
USE `vehicle_tracker`;<br>
### Create persons table
CREATE TABLE person (<br>
    id BIGINT AUTO_INCREMENT PRIMARY KEY,<br>
    name VARCHAR(255) NOT NULL,<br>
    address VARCHAR(255) NOT NULL,<br>
    egn VARCHAR(255) NOT NULL,<br>
    gender VARCHAR(255) NOT NULL<br>
);
### Create vehicles table
CREATE TABLE vehicle (<br>
    id BIGINT AUTO_INCREMENT PRIMARY KEY,<br>
    plate_number VARCHAR(20) UNIQUE NOT NULL,<br>
    model VARCHAR(255) NOT NULL,<br>
    color VARCHAR(255) NOT NULL,<br>
    engine_code VARCHAR(255) UNIQUE NOT NULL<br>
);
### Create ownerships table
CREATE TABLE ownership (<br>
    id BIGINT AUTO_INCREMENT PRIMARY KEY,<br>
    person_id BIGINT NOT NULL,<br>
    vehicle_id BIGINT NOT NULL,<br>
    acquisition_date DATETIME NOT NULL,<br>
    termination_date DATETIME,<br>
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE,<br>
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)<br>
);
### Create violations table
CREATE TABLE violation (<br>
    id BIGINT AUTO_INCREMENT PRIMARY KEY,<br>
    act TEXT,<br>
    violation_type ENUM(<br>
    'SPEEDING', <br>
    'RUNNING_RED_LIGHT',<br>
    'RECKLESS_DRIVING',<br>
    'NO_LICENSE',<br>
    'DRUNK_DRIVING'),<br>
    time_stamp DATETIME NOT NULL,<br>
    person_id BIGINT,<br>
    vehicle_id BIGINT,<br>
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE,<br>
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)<br>
);
### Insert persons
INSERT INTO person (name, address, egn, gender) VALUES<br>
    ('Petar Dimitrov', 'Targovishte, Bulgaria', '5112072341', 'male'),<br>
    ('Edin Gospodin', 'Targovishte, Bulgaria', '9712102345', 'male');

### Insert vehicles
INSERT INTO vehicle (plate_number, model, color, engine_code) VALUES<br>
    ('T9952AK', 'ford', 'сив', 'QJBB'),<br>
    ('T0641CT', 'ferrari', 'червен', 'DW10TD');<br>

### Insert ownerships
INSERT INTO ownership (person_id, vehicle_id, acquisition_date, termination_date) VALUES<br>
    (1, 1, '2001-12-02 14:10:00', DATE_ADD('2001-12-02 14:10:00', INTERVAL FLOOR(1 + RAND() * 20) YEAR)),<br>
    (1, 2, '2001-05-03 15:25:00', DATE_ADD('2001-05-03 15:25:00', INTERVAL FLOOR(1 + RAND() * 20) YEAR)),<br>
    (2, 2, '2019-12-02 00:00:00', DATE_ADD('2019-12-02 00:00:00', INTERVAL FLOOR(1 + RAND() * 20) YEAR));

### Insert violations
INSERT INTO violation (act, violation_type, time_stamp, person_id, vehicle_id) VALUES<br>
    ('ACT91372', 'SPEEDING', '2002-05-10 14:03:23', 1, 1),<br>
    ('ACT92372', 'NO_LICENSE', '2002-03-21 11:25:00', 1, 1),<br>
    ('ACT93372', 'NO_LICENSE', '2023-05-21 16:13:00', 1, 1),<br>
    ('ACT94372', 'NO_LICENSE', '2002-05-21 16:13:00', 1, 1);

## Example queries for testing the database

* ### Retrieve violations from 2002 with details
SELECT <br>
    v.act,<br>
    v.violation_type,<br>
    v.time_stamp,<br>
    p.name as offender_name<br>
FROM violation v<br>
JOIN person p ON v.person_id = p.id<br>
WHERE YEAR(v.time_stamp) = 2002;<br>

* ### Retrieve names and addresses of red Ferrari owners
SELECT DISTINCT p.name, p.address <br>
FROM person p<br>
JOIN ownership o ON p.id = o.person_id<br>
JOIN vehicle v ON o.vehicle_id = v.id<br>
WHERE v.model = 'ferrari' <br>
AND v.color = 'червен';

* ### Retrieve violation types and counts for 2002
SELECT violation_type, COUNT(*) as violation_count<br>
FROM violation<br>
WHERE YEAR(time_stamp) = 2002<br>
GROUP BY violation_type;<br>

* ### Remove all data for owner with EGN 5112072341
DELETE FROM person WHERE egn = '5112072341';
