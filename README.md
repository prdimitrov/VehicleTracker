# VehicleTracker Application

## Example queries

### 1. Retrieve a list of violations (act number, violation type, date, and offender name) committed in 2002
**GET request:**
http://localhost:8080/api/violations?year=2002

### 2. Retrieve the names and addresses of all owners of red Ferrari cars
**GET request:**
http://localhost:8080/api/owners?model=ferrari&color=червен

### 3. Retrieve violation types and the number of violations of each type committed in 2002
**GET request:**
http://localhost:8080/api/violations/stats?year=2002

### 4. Remove all data from database for owner with EGN 5112072341
**DELETE query:**
http://localhost:8080/api/persons?egn=5112072341

## Database creation with test data
-- Create database
CREATE DATABASE `vehicle_tracker`;
-- Use vehicle_tracker database
USE `vehicle_tracker`;
-- Create persons table
CREATE TABLE person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    egn VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL
);
-- Create vehicles table
CREATE TABLE vehicle (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    plate_number VARCHAR(20) UNIQUE NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    engine_code VARCHAR(255) UNIQUE NOT NULL
);
-- Create ownerships table
CREATE TABLE ownership (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    acquisition_date DATETIME NOT NULL,
    termination_date DATETIME,
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);
-- Create violations table
CREATE TABLE violation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    act TEXT,
    violation_type ENUM(
    'SPEEDING', 
    'RUNNING_RED_LIGHT',
    'RECKLESS_DRIVING',
    'NO_LICENSE',
    'DRUNK_DRIVING'),
    time_stamp DATETIME NOT NULL,
    person_id BIGINT,
    vehicle_id BIGINT,
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);
-- Insert persons
INSERT INTO person (name, address, egn, gender) VALUES
    ('Petar Dimitrov', 'Targovishte, Bulgaria', '5112072341', 'male'),
    ('Edin Gospodin', 'Targovishte, Bulgaria', '9712102345', 'male');

-- Insert vehicles
INSERT INTO vehicle (plate_number, model, color, engine_code) VALUES
    ('T9952AK', 'ford', 'сив', 'QJBB'),
    ('T0641CT', 'ferrari', 'червен', 'DW10TD');

-- Insert ownerships
INSERT INTO ownership (person_id, vehicle_id, acquisition_date, termination_date) VALUES
    (1, 1, '2001-12-02 14:10:00', DATE_ADD('2001-12-02 14:10:00', INTERVAL FLOOR(1 + RAND() * 20) YEAR)),
    (1, 2, '2001-05-03 15:25:00', DATE_ADD('2001-05-03 15:25:00', INTERVAL FLOOR(1 + RAND() * 20) YEAR)),
    (2, 2, '2019-12-02 00:00:00', DATE_ADD('2019-12-02 00:00:00', INTERVAL FLOOR(1 + RAND() * 20) YEAR));

-- Insert violations
INSERT INTO violation (act, violation_type, time_stamp, person_id, vehicle_id) VALUES
    ('ACT91372', 'SPEEDING', '2002-05-10 14:03:23', 1, 1),
    ('ACT92372', 'NO_LICENSE', '2002-03-21 11:25:00', 1, 1),
    ('ACT93372', 'NO_LICENSE', '2023-05-21 16:13:00', 1, 1),
    ('ACT94372', 'NO_LICENSE', '2002-05-21 16:13:00', 1, 1);

## Example queries for testing the database

## 4. Retrieve violations from 2002 with details
SELECT 
    v.act,
    v.violation_type,
    v.time_stamp,
    p.name as offender_name
FROM violation v
JOIN person p ON v.person_id = p.id
WHERE YEAR(v.time_stamp) = 2002;

## 3. Retrieve names and addresses of red Ferrari owners
SELECT DISTINCT p.name, p.address 
FROM person p
JOIN ownership o ON p.id = o.person_id
JOIN vehicle v ON o.vehicle_id = v.id
WHERE v.model = 'ferrari' 
AND v.color = 'червен';

## 2. Retrieve violation types and counts for 2002
SELECT violation_type, COUNT(*) as violation_count
FROM violation
WHERE YEAR(time_stamp) = 2002
GROUP BY violation_type;

## 1. Remove all data for owner with EGN 5112072341
DELETE FROM violation 
WHERE person_id = (SELECT id FROM person WHERE egn = '5112072341');
DELETE FROM ownership 
WHERE person_id = (SELECT id FROM person WHERE egn = '5112072341');
DELETE FROM person 
WHERE egn = '5112072341';
