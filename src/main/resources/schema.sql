drop table if exists currency cascade;
drop table if exists market cascade;
drop table if exists product cascade;
drop table if exists product_categories cascade;
drop table if exists product_category cascade;
drop table if exists store cascade;
drop table if exists store_section cascade;
drop table if exists store_section_entry cascade;
drop table if exists tax_category cascade;
drop table if exists tax_scheme cascade;
drop table if exists tax_scheme_tax_categories cascade;
create table currency
(
    multiplier integer,
    symbol     varchar(255) not null,
    primary key (symbol)
);
create table market
(
    currency_symbol varchar(255) unique,
    name            varchar(255) not null,
    tax_scheme_name varchar(255) unique,
    primary key (name)
);
create table product
(
    id          bigint generated by default as identity,
    description varchar(255),
    keywords    varchar(255),
    name        varchar(255),
    primary key (id)
);
create table product_categories
(
    product_id      bigint       not null,
    categories_name varchar(255) not null,
    primary key (product_id, categories_name)
);
create table product_category
(
    description varchar(255),
    name        varchar(255) not null,
    primary key (name)
);
create table store
(
    market_name varchar(255),
    name        varchar(255) not null,
    primary key (name)
);
create table store_section
(
    id          bigint generated by default as identity,
    description varchar(255),
    name        varchar(255),
    store_name  varchar(255),
    primary key (id)
);
create table store_section_entry
(
    price             integer,
    id                bigint generated by default as identity,
    product_id        bigint,
    store_section_id  bigint,
    tax_category_name varchar(255),
    primary key (id)
);
create table tax_category
(
    percent integer,
    name    varchar(255) not null,
    primary key (name)
);
create table tax_scheme
(
    name varchar(255) not null,
    primary key (name)
);
create table tax_scheme_tax_categories
(
    tax_categories_name varchar(255) not null,
    tax_scheme_name     varchar(255) not null,
    primary key (tax_categories_name, tax_scheme_name)
);
alter table if exists market
    add constraint FK3ckr7rhm4mg2fnnodvdtleksw foreign key (currency_symbol) references currency;
alter table if exists market
    add constraint FKd6219no52q28gfktdcbh1oy8a foreign key (tax_scheme_name) references tax_scheme;
alter table if exists product_categories
    add constraint FKr6crqpw4iespti4r2s0sr463g foreign key (categories_name) references product_category;
alter table if exists product_categories
    add constraint FKppc5s0f38pgb35a32dlgyhorc foreign key (product_id) references product;
alter table if exists store
    add constraint FKjm83dn33t21iuv28uqjc0r3ac foreign key (market_name) references market;
alter table if exists store_section
    add constraint FKofml64g04g61p31kxidy4sqt0 foreign key (store_name) references store;
alter table if exists store_section_entry
    add constraint FK8odhvbjt7vmbh49ayxv145fda foreign key (product_id) references product;
alter table if exists store_section_entry
    add constraint FKfmals3b8y0s6rwqp6vgijjpje foreign key (store_section_id) references store_section;
alter table if exists store_section_entry
    add constraint FKiix0hyc8ht6mnd1xqxrpu978j foreign key (tax_category_name) references tax_category;
alter table if exists tax_scheme_tax_categories
    add constraint FK2mm2s0i83h5e53xsmm6q49scb foreign key (tax_categories_name) references tax_category;
alter table if exists tax_scheme_tax_categories
    add constraint FKnf3de54t1aekc0cimxa4kmier foreign key (tax_scheme_name) references tax_scheme;
