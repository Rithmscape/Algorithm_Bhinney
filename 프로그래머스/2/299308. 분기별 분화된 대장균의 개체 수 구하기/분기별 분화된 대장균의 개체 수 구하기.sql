select concat(quarter(differentiation_date), 'Q') as quarter,
    count(id) as ecoli_count
from ecoli_data
group by 1
order by 1