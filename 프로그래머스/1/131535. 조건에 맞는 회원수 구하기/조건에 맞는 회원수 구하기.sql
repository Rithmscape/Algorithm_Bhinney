select count(*) as users
from user_info
where (age between 20 and 29)
and (to_char(joined, 'YYYY') = '2021')