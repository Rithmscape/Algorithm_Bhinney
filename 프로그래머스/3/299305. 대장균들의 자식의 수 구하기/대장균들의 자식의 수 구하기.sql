select a.id, count(b.id) as child_count
from ecoli_data as a
    left join ecoli_data as b
    on a.id = b.parent_id
group by a.id
order by a.id asc