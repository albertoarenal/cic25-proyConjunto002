drop table if exists barco cascade
drop sequence if exists barco_seq
create sequence barco_seq start with 1 increment by 50
    create table barco (
        anio integer,
        capacidad integer,
        id bigint not null,
        version bigint not null,
        material varchar(255),
        tipo_barco varchar(255),
        primary key (id)
    )