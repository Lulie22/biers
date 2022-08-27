
insert into bestelbonlijnen(bestelbonId,bierId,aantal,prijs)
VALUES ((select id from bestelbonnen where naam = 'test1'),
        (select id from bieren where naam = 'test1'),2,
        (select prijs from bieren where naam = 'test1'));