select student.name, student.age, faculty.name
from student
         left join faculty on faculty.id = student.faculty_id;

select student.name
from student
         inner join avatar on student.id = avatar.student_id