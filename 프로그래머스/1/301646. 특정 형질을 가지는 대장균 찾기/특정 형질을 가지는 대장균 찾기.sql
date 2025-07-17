select count(*) as count
from ecoli_data
where (genotype & 2 = 0)
and (genotype & 1 > 0 or genotype & 4 > 0)