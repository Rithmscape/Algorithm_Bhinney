select i.item_id, item_name, rarity
from item_info i 
left outer join item_tree t
on i.item_id = t.item_id
where parent_item_id in (
                            select item_id
                            from item_info
                            where rarity = "rare"
                        )
order by 1 desc