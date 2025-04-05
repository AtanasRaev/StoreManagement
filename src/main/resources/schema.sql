create table admin_settings (
                                id integer primary key autoincrement,
                                password TEXT not null
);

create table products (
                          id integer primary key autoincrement,
                          image varchar not null,
                          is_selected boolean not null,
                          is_synced boolean not null,
                          last_updated datetime not null,
                          name varchar not null,
                          price numeric not null,
                          quantity numeric not null,
                          type varchar not null,
                          unit varchar not null
);

create table purchases (
                           id integer primary key autoincrement,
                           product_id integer not null,
                           quantity numeric(10,2) not null,
                           price_at_time numeric(10,2) not null,
                           created_at datetime not null,
                           is_synced boolean not null,
                           foreign key (product_id) references products(id)
);

