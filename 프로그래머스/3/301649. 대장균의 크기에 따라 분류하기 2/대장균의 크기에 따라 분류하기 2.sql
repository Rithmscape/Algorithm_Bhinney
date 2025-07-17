select 
    id, 
    case 
        when q = 1 then 'CRITICAL'
        when q = 2 then 'HIGH'
        when q = 3 then 'MEDIUM'
        when q = 4 then 'LOW'
    end as colony_name
from (
    select id, ntile(4) over (order by size_of_colony desc) as q
    from ecoli_data
) ranked
order by id