insert into regra_atraso (dias_atraso_min, dias_atraso_max, multa, juros_dia) values (0, 3, 2, 0.1) on conflict do nothing;
insert into regra_atraso (dias_atraso_min, dias_atraso_max, multa, juros_dia) values (4, 5, 3, 0.2) on conflict do nothing;
insert into regra_atraso (dias_atraso_min, dias_atraso_max, multa, juros_dia) values (6, 99999, 5, 0.3) on conflict do nothing;
commit;