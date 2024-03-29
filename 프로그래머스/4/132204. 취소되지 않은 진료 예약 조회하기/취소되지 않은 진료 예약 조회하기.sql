select a.apnt_no, p.pt_name, a.pt_no, a.mcdp_cd, d.dr_name, a.apnt_ymd
from appointment as a
join patient as p on a.pt_no = p.pt_no
join doctor as d on a.mddr_id = d.dr_id
where a.mcdp_cd like "CS"
    and a.apnt_cncl_yn like "N" 
    and a.apnt_ymd like "2022-04-13%"
order by a.apnt_ymd