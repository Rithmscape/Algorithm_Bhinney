select 
    b.category, 
    sum(c.sales) as total_sales
from book as b
join book_sales as c
    on b.book_id = c.book_id
where c.sales_date like "2022-01%"
group by b.category
order by b.category