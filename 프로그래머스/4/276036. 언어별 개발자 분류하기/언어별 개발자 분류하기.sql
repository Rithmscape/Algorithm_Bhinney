SELECT 
    CASE 
        WHEN d.skill_code & f.skill_code
             AND d.skill_code & (SELECT code FROM skillcodes WHERE name = 'Python')
             THEN 'A'
        WHEN d.skill_code & (SELECT code FROM skillcodes WHERE name = 'C#')
             THEN 'B'
        WHEN d.skill_code & f.skill_code
             THEN 'C'
        ELSE NULL
    END AS grade,
    d.id, 
    d.email
FROM developers d
CROSS JOIN (
    SELECT SUM(code) AS skill_code 
    FROM skillcodes 
    WHERE category = 'Front End'
) f
HAVING grade IS NOT NULL
ORDER BY grade, d.id;