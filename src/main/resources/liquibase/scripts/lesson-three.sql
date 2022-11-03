--liquibase formatted sql

--changeset iivanov:1
CREATE INDEX name_index on student (name);

--changeset iivanov:2
CREATE INDEX name_faculty_index on faculty (name, color);