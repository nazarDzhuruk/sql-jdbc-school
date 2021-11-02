SELECT group_id, COUNT(student_id)
FROM students
GROUP BY group_id, student_id
HAVING COUNT(student_id) <= 10
