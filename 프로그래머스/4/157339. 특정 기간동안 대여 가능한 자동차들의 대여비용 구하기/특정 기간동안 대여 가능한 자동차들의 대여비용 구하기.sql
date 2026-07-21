# car_rental_company_car as c
# car_rental_company_rental_history as h
# car_rental_company_discount_plan as d

select 
    c.car_id,
    c.car_type,
    floor(c.daily_fee * (1 - d.discount_rate / 100) * 30) fee
from car_rental_company_car c
join car_rental_company_discount_plan d
    on c.car_type = d.car_type
    and d.duration_type = '30일 이상'
where c.car_type in ('세단', 'SUV')
    and c.car_id not in 
        (select car_id
         from car_rental_company_rental_history
         where start_date <= '2022-11-30'
            and end_date >= '2022-11-01')
    and floor(c.daily_fee * (1 - d.discount_rate / 100) * 30) between 500000 and 1999999 
order by fee desc, car_type asc, car_id desc
