select distinct
    d.id,
    d.email, 
    d.first_name, 
    d.last_name
from developers as d
join skillcodes as s
on d.skill_code & s.code > 0
where s.name in ('Python', 'C#')
order by d.id