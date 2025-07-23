drop table if exists barco cascade
drop table if exists capitan cascade
drop sequence if exists barco_seq
drop sequence if exists capitan_seq
create sequence barco_seq start with 1 increment by 50
create sequence capitan_seq start with 1 increment by 50

    create table barco (
        anio integer,
        capacidad integer,
        id bigint not null,
        version bigint not null,
        material varchar(255),
        tipo_barco varchar(255),
        primary key (id)
    )

    create table capitan (
        barco_id bigint unique,
        id bigint not null,
        numero_de_licencia varchar(8) unique,
        version bigint not null,
        apellidos varchar(255),
        nombre varchar(255),
        primary key (id)
    )

    alter table if exists capitan
       add constraint FKqrci20v6hdcy7m1vmyymrsenl
       foreign key (barco_id)
       references barco