select id, email, first_name, last_name
from developer_infos
where 'Python' in (skill_1, skill_2, skill_3)
order by id

# select id, email, first_name, last_name 
# from developer_infos
# where skill_1 like 'Python'
#     or skill_2 like 'Python'
#     or skill_3 like 'Python'
# order by id