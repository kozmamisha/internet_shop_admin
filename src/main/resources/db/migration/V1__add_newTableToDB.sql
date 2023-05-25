create table addresses
(
    id        bigint not null auto_increment,
    building  varchar(255),
    city      varchar(255),
    country   varchar(255),
    client_id bigint,
    primary key (id)
) engine = InnoDB;
create table attribute_has_product
(
    id           bigint not null auto_increment,
    attribute_id bigint,
    productes_id bigint,
    primary key (id)
) engine = InnoDB;
create table attributes
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(255),
    unit        varchar(255),
    primary key (id)
) engine = InnoDB;
create table category
(
    id          bigint not null auto_increment,
    description varchar(255),
    image       varchar(255),
    name        varchar(255),
    primary key (id)
) engine = InnoDB;
create table clients
(
    id         bigint  not null,
    age        integer not null,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    phone      integer not null,
    primary key (id)
) engine = InnoDB;
create table fix_role
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;
create table orders
(
    id           bigint not null auto_increment,
    date_created datetime(6),
    delivery     varchar(255),
    payment      varchar(255),
    status_order bit    not null,
    clients_id   bigint,
    primary key (id)
) engine = InnoDB;
create table product_has_order
(
    id         bigint  not null auto_increment,
    quantity   integer not null,
    order_id   bigint,
    product_id bigint,
    primary key (id)
) engine = InnoDB;
create table products
(
    id          bigint not null auto_increment,
    description varchar(255),
    image       varchar(255),
    name        varchar(255),
    price       decimal(38, 2),
    category_id bigint,
    primary key (id)
) engine = InnoDB;
create table users
(
    id       bigint not null auto_increment,
    password varchar(255),
    username varchar(255),
    primary key (id)
) engine = InnoDB;
create table users_roles
(
    users_set_id bigint not null,
    roles_id     bigint not null,
    primary key (users_set_id, roles_id)
) engine = InnoDB;
alter table addresses
    add constraint FK_client_id foreign key (client_id) references clients (id);
alter table attribute_has_product
    add constraint FK_attribute_id foreign key (attribute_id) references attributes (id);
alter table attribute_has_product
    add constraint FK_productes_id foreign key (productes_id) references products (id);
alter table clients
    add constraint FK_user_client_id foreign key (id) references users (id);
alter table orders
    add constraint FK_clients_id foreign key (clients_id) references clients (id);
alter table product_has_order
    add constraint FK_order_id foreign key (order_id) references orders (id);
alter table product_has_order
    add constraint FK_product_id foreign key (product_id) references products (id);
alter table products
    add constraint FK_category_id foreign key (category_id) references category (id);
alter table users_roles
    add constraint FK_roles_id foreign key (roles_id) references fix_role (id);
alter table users_roles
    add constraint FK_users_set_id foreign key (users_set_id) references users (id);