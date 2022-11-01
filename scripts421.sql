ALTER TABLE student
    ADD CHECK ( age >= 16 ),
    ALTER column name set not null,
    add constraint unique_name unique (name),
    alter column age set default 20;