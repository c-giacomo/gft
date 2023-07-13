SELECT
    b.id AS bus_id,
    COUNT(p.id) AS passengers_on_board
FROM
    buses b
LEFT JOIN
    passengers p ON b.origin = p.origin AND b.destination = p.destination AND b.time >= p.time
GROUP BY
    b.id
ORDER BY
    b.id ASC;