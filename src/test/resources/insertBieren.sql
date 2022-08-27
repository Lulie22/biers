insert into bieren (naam, brouwerId, alcohol, prijs, besteld)
VALUES ('test1',(select id from brouwers where naam = 'test1'), 5, 10, 0);
insert into bieren (naam, brouwerId, alcohol, prijs, besteld)
VALUES ('test2',(select id from brouwers where naam = 'test2'), 7, 12, 0);