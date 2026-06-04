select c.id 
from ecoli_data as c
join ecoli_data as b
    on c.parent_id = b.id
join ecoli_data as a
    on b.parent_id = a.id
where a.parent_id is null
order by c.id asc